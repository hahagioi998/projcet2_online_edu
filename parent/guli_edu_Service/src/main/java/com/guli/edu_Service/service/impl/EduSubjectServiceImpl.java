package com.guli.edu_Service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.common.Result;
import com.guli.edu_Service.MyExceptions.MyException;
import com.guli.edu_Service.Vo.subjectVo.SubjectNestedVo;
import com.guli.edu_Service.Vo.subjectVo.SubjectVo;
import com.guli.edu_Service.bean.EduSubject;
import com.guli.edu_Service.mapper.EduSubjectMapper;
import com.guli.edu_Service.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author xx
 * @since 2020-10-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

//    @Autowired
//    private EduSubjectMapper eduSubjectMapper;


    @Override
    public List<String> importSubject(MultipartFile file) {

        ArrayList<String> msg = new ArrayList<>();

        try {


            //s会用03的 xls数据
            HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());

            HSSFSheet sheet = workbook.getSheetAt(0);

            int last = sheet.getLastRowNum();
            System.err.println("getLastRowNum :   " + last);


            for (int i = 0; i < 10; i++) {

            }

            for (int i = 1; i <= last; i++) {
                System.err.println("当前的行数" + i);
                HSSFRow row = sheet.getRow(i);

                if (row == null) {

                    msg.add("第" + i + "行数据为空,方法停止 ，请调整后在再试");

                    continue;//  TODO
                }

                HSSFCell cell1 = row.getCell(0);

                if (cell1 == null) {
                    msg.add("第" + i + "行，第1列数据为空 ，请检查数据");
                    continue;  //  TODO
                }

                String cellValue1 = cell1.getStringCellValue();


                EduSubject exit = this.existOnSubject(cellValue1);


                String id_parent = null;

                if (exit == null) {

                    EduSubject subject = new EduSubject();
                    subject.setParentId("0");
                    subject.setTitle(cellValue1);
                    subject.setSort(0);

                    baseMapper.insert(subject);

                    id_parent = subject.getId();


                } else {

                    id_parent = exit.getId();

                    System.err.println("id_parent : " + id_parent);

                    EduSubject sub = new EduSubject();
                    sub.setTitle(cellValue1);
                    sub.setParentId(id_parent + "");


                }


                HSSFCell cell2 = row.getCell(1);

                if (cell2 == null) {

                    msg.add("第" + i + "行，第2列数据为空 ，请检查数据");

                    continue; //  TODO
                }

                String cellValue2 = cell2.getStringCellValue();

                EduSubject subjectTwo = this.exitTwoSubject(cellValue2, id_parent);

                if (subjectTwo == null) {

                    EduSubject subject2 = new EduSubject();

                    subject2.setParentId(id_parent);
                    subject2.setTitle(cellValue2);
                    subject2.setSort(0);

                    baseMapper.insert(subject2);

//                    return msg;

                }


            }

            return msg;


        } catch (Exception e) {

            e.printStackTrace();

            throw new MyException(20001, "出现异常导入失败");
        }


    }


    private EduSubject existOnSubject(String name) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("parent_id", 0);
        wrapper.eq("title", name);


        return baseMapper.selectOne(wrapper);
    }


    private EduSubject exitTwoSubject(String name, String pid) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<EduSubject>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);

        return baseMapper.selectOne(wrapper);


    }


    @Override
    public List<SubjectNestedVo> nestedList() {


        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();

        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> subjects = baseMapper.selectList(queryWrapper);

        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> subSubjects = baseMapper.selectList(queryWrapper2);

        //填充一级分类vo数据
        int count = subjects.size();
        for (int i = 0; i < count; i++) {
            EduSubject subject = subjects.get(i);

            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(subject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);

            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            int count2 = subSubjects.size();
            for (int j = 0; j < count2; j++) {

                EduSubject subSubject = subSubjects.get(j);
                if (subject.getId().equals(subSubject.getParentId())) {

                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(subSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }


        return subjectNestedVoArrayList;


    }

    @Override
    public boolean deleteSubjectById(String id) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", id);
        Integer count = baseMapper.selectCount(wrapper);

        System.err.println("deleteSubjectById  Integer count : " + count);

        if (count > 0) {

            return false;
        }
        int i = baseMapper.deleteById(id);
        System.err.println("deleteSubjectById  Integer i " + i);
        return  i > 0;

    }

    @Override  //添加一级分类
    public boolean saveOneLevel(EduSubject eduSubject) {

//        private EduSubject existOnSubject(String name) {

        EduSubject exit = this.existOnSubject(eduSubject.getTitle());

        if (exit == null) {
            return baseMapper.insert(eduSubject.setParentId("0")) > 0;

        }

        return false;


    }

    @Override //添加二级分类
    public boolean saveTwoLevel(EduSubject eduSubject) {

        EduSubject exit = this.exitTwoSubject(eduSubject.getTitle(), eduSubject.getParentId());

        if (exit == null ){

            return baseMapper.insert(eduSubject) > 0;
        }


        return false;
    }

}
