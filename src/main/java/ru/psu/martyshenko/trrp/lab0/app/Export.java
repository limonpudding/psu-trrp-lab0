package ru.psu.martyshenko.trrp.lab0.app;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import ru.psu.martyshenko.trrp.lab0.pg.tables.pojos.*;
import ru.psu.martyshenko.trrp.lab0.service.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Export {

    BuildingService buildingService;
    ClassroomService classroomService;
    TeacherService teacherService;
    CourseService courseService;
    LessonService lessonService;

    public Export() {
        buildingService = new BuildingService();
        classroomService = new ClassroomService();
        teacherService = new TeacherService();
        courseService = new CourseService();
        lessonService = new LessonService();
    }

    public void runExport() throws IOException {
        HSSFWorkbook wb = new HSSFWorkbook();

        createBuildingPage(wb);
        createClassroomPage(wb);
        createTeacherPage(wb);
        createCoursePage(wb);
        createLessonPage(wb);

        wb.write(new FileOutputStream(new File("ExportedData.xls")));
        wb.close();
    }

    private void createBuildingPage(HSSFWorkbook wb) {
        HSSFSheet buildingSheet = wb.createSheet("Building");
        List<Building> buildingList = buildingService.getAll();

        HSSFRow rowHeader = buildingSheet.createRow(0);
        HSSFCell buildingCellHeader = rowHeader.createCell(0);
        HSSFCell buildingAddressCellHeader = rowHeader.createCell(1);
        buildingCellHeader.setCellValue("Корпус");
        buildingAddressCellHeader.setCellValue("Адрес");

        int counter = 1;
        for (Building building:buildingList) {
            HSSFRow row = buildingSheet.createRow(counter);
            HSSFCell buildingCell = row.createCell(0);
            HSSFCell buildingAddressCell = row.createCell(1);
            buildingCell.setCellValue(building.getBuilding());
            buildingAddressCell.setCellValue(building.getBuildingAddress());
            counter++;
        }
        buildingSheet.autoSizeColumn(0);
        buildingSheet.autoSizeColumn(1);
    }

    private void createClassroomPage(HSSFWorkbook wb) {
        HSSFSheet classroomSheet = wb.createSheet("Classroom");
        List<Classroom> classroomList = classroomService.getAll();

        HSSFRow rowHeader = classroomSheet.createRow(0);
        HSSFCell classroomIdCellHeader = rowHeader.createCell(0);
        HSSFCell classroomNumberCellHeader = rowHeader.createCell(1);
        HSSFCell buildingCellHeader = rowHeader.createCell(2);
        classroomIdCellHeader.setCellValue("Идентификатор");
        classroomNumberCellHeader.setCellValue("Номер");
        buildingCellHeader.setCellValue("Корпус");

        int counter = 1;
        for (Classroom classroom:classroomList) {
            HSSFRow row = classroomSheet.createRow(counter);
            HSSFCell classroomIdCell = row.createCell(0);
            HSSFCell classroomNumberCell = row.createCell(1);
            HSSFCell buildingCell = row.createCell(2);
            classroomIdCell.setCellValue(classroom.getClassroomId());
            classroomNumberCell.setCellValue(classroom.getClassroomNumber());
            buildingCell.setCellValue(classroom.getBuilding());
            counter++;
        }
        classroomSheet.autoSizeColumn(0);
        classroomSheet.autoSizeColumn(1);
        classroomSheet.autoSizeColumn(2);
    }

    private void createTeacherPage(HSSFWorkbook wb) {
        HSSFSheet teacherSheet = wb.createSheet("Teacher");
        List<Teacher> teacherList = teacherService.getAll();

        HSSFRow rowHeader = teacherSheet.createRow(0);
        HSSFCell teacherIdCellHeader = rowHeader.createCell(0);
        HSSFCell teacherLastNameCellHeader = rowHeader.createCell(1);
        HSSFCell teacherFirstNameCellHeader = rowHeader.createCell(2);
        HSSFCell teacherPatronymicsCellHeader = rowHeader.createCell(3);
        HSSFCell teacherMailCellHeader = rowHeader.createCell(4);
        HSSFCell teacherPhoneCellHeader = rowHeader.createCell(5);
        teacherIdCellHeader.setCellValue("Идентификатор");
        teacherLastNameCellHeader.setCellValue("Фамилия");
        teacherFirstNameCellHeader.setCellValue("Имя");
        teacherPatronymicsCellHeader.setCellValue("Отчество");
        teacherMailCellHeader.setCellValue("Эл. почта");
        teacherPhoneCellHeader.setCellValue("Телефон");

        int counter = 1;
        for (Teacher teacher:teacherList) {
            HSSFRow row = teacherSheet.createRow(counter);
            HSSFCell teacherIdCell = row.createCell(0);
            HSSFCell teacherLastNameCell = row.createCell(1);
            HSSFCell teacherFirstNameCell = row.createCell(2);
            HSSFCell teacherPatronymicsCell = row.createCell(3);
            HSSFCell teacherMailCell = row.createCell(4);
            HSSFCell teacherPhoneCell = row.createCell(5);
            teacherIdCell.setCellValue(teacher.getTeacherId());
            teacherLastNameCell.setCellValue(teacher.getTeacherLastName());
            teacherFirstNameCell.setCellValue(teacher.getTeacherFirstName());
            teacherPatronymicsCell.setCellValue(teacher.getTeacherPatronymic());
            teacherMailCell.setCellValue(teacher.getTeacherMail());
            teacherPhoneCell.setCellValue(teacher.getTeacherPhone());
            counter++;
        }
        teacherSheet.autoSizeColumn(0);
        teacherSheet.autoSizeColumn(1);
        teacherSheet.autoSizeColumn(2);
        teacherSheet.autoSizeColumn(3);
        teacherSheet.autoSizeColumn(4);
        teacherSheet.autoSizeColumn(5);
    }

    private void createCoursePage(HSSFWorkbook wb) {
        HSSFSheet courseSheet = wb.createSheet("Course");
        List<Course> courseList = courseService.getAll();

        HSSFRow rowHeader = courseSheet.createRow(0);
        HSSFCell courseIdCellHeader = rowHeader.createCell(0);
        HSSFCell courseNameCellHeader = rowHeader.createCell(1);
        HSSFCell courseTotalHoursCellHeader = rowHeader.createCell(2);
        HSSFCell courseTeacherIdCellHeader = rowHeader.createCell(3);
        courseIdCellHeader.setCellValue("Идентификатор");
        courseNameCellHeader.setCellValue("Название курса");
        courseTotalHoursCellHeader.setCellValue("Трудоёмкость (часов)");
        courseTeacherIdCellHeader.setCellValue("Ссылка на преподавателя");

        int counter = 1;
        for (Course course:courseList) {
            HSSFRow row = courseSheet.createRow(counter);
            HSSFCell courseIdCell = row.createCell(0);
            HSSFCell courseNameCell = row.createCell(1);
            HSSFCell courseTotalHoursCell = row.createCell(2);
            HSSFCell courseTeacherIdCell = row.createCell(3);
            courseIdCell.setCellValue(course.getCourseId());
            courseNameCell.setCellValue(course.getCourseName());
            courseTotalHoursCell.setCellValue(course.getCourseTotalHours());
            courseTeacherIdCell.setCellValue(course.getTeacherId());
            counter++;
        }
        courseSheet.autoSizeColumn(0);
        courseSheet.autoSizeColumn(1);
        courseSheet.autoSizeColumn(2);
        courseSheet.autoSizeColumn(3);
    }

    private void createLessonPage(HSSFWorkbook wb) {
        HSSFSheet lessonSheet = wb.createSheet("Lesson");
        List<Lesson> lessonList = lessonService.getAll();

        HSSFRow rowHeader = lessonSheet.createRow(0);
        HSSFCell lessonIdCellHeader = rowHeader.createCell(0);
        HSSFCell lessonDateTimeCellHeader = rowHeader.createCell(1);
        HSSFCell lessonHoursCellHeader = rowHeader.createCell(2);
        HSSFCell courseIdCellHeader = rowHeader.createCell(3);
        HSSFCell classroomIdCellHeader = rowHeader.createCell(4);
        lessonIdCellHeader.setCellValue("Идентификатор");
        lessonDateTimeCellHeader.setCellValue("Дата и время проведения занятия");
        lessonHoursCellHeader.setCellValue("Длительночть (часов)");
        courseIdCellHeader.setCellValue("Ссылка на курс");
        classroomIdCellHeader.setCellValue("Ссылка на аудиторию");

        int counter = 1;
        for (Lesson lesson:lessonList) {
            HSSFRow row = lessonSheet.createRow(counter);
            HSSFCell lessonIdCell = row.createCell(0);
            HSSFCell lessonDateTimeCell = row.createCell(1);
            HSSFCell lessonHoursCell = row.createCell(2);
            HSSFCell courseIdCell = row.createCell(3);
            HSSFCell classroomIdCell = row.createCell(4);
            lessonIdCell.setCellValue(lesson.getLessonId());

            DataFormat format = wb.createDataFormat();
            CellStyle dateStyle = wb.createCellStyle();
            dateStyle.setDataFormat(format.getFormat("yyyy-mm-dd hh:MM:ss"));
            lessonDateTimeCell.setCellStyle(dateStyle);
            lessonDateTimeCell.setCellValue(lesson.getLessonDatetime());

            lessonHoursCell.setCellValue(lesson.getLessonHours());
            courseIdCell.setCellValue(lesson.getCourseId());
            classroomIdCell.setCellValue(lesson.getClassroomId());
            counter++;
        }
        lessonSheet.autoSizeColumn(0);
        lessonSheet.autoSizeColumn(1);
        lessonSheet.autoSizeColumn(2);
        lessonSheet.autoSizeColumn(3);
        lessonSheet.autoSizeColumn(4);
    }
}
