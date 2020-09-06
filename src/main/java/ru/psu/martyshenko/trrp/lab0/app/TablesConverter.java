package ru.psu.martyshenko.trrp.lab0.app;

import ru.psu.martyshenko.trrp.lab0.fb.tables.pojos.PsuCourses;
import ru.psu.martyshenko.trrp.lab0.pg.tables.pojos.*;
import ru.psu.martyshenko.trrp.lab0.service.*;

import java.util.List;

public class TablesConverter {

    BuildingService buildingService;
    ClassroomService classroomService;
    TeacherService teacherService;
    CourseService courseService;
    LessonService lessonService;

    public TablesConverter() {
        buildingService = new BuildingService();
        classroomService = new ClassroomService();
        teacherService = new TeacherService();
        courseService = new CourseService();
        lessonService = new LessonService();
    }

    public void convert() {
        PsuCoursesService psuCoursesDao = new PsuCoursesService();
        List<PsuCourses> coursesList = psuCoursesDao.getAllCourses();

        for (PsuCourses row:coursesList) {
            Integer classroomId = null;
            Integer teacherId = null;
            Integer courseId = null;
            saveBuilding(row);
            classroomId = saveClassroom(row);
            teacherId = saveTeacher(row);
            courseId = saveCourse(row, teacherId);
            saveLesson(row, courseId, classroomId);

        }
    }


    public void saveBuilding(PsuCourses psuCourses) {
        Building building = new Building(psuCourses.getBuilding(), psuCourses.getBuildingAddress());
        buildingService.insert(building);
    }

    public Integer saveClassroom(PsuCourses psuCourses) {
        Classroom classroom = new Classroom(null, psuCourses.getClassroomNumber(), psuCourses.getBuilding());
        Integer classroomId = classroomService.insert(classroom);
        return classroomId;
    }

    public Integer saveTeacher(PsuCourses psuCourses) {
        String[] fio = psuCourses.getTeacherName().split(" ");
        Teacher teacher = new Teacher(null, fio[0], fio[1], fio[2], psuCourses.getTeacherMail(), psuCourses.getTeacherPhone());
        Integer teacherId = teacherService.insert(teacher);
        return teacherId;
    }

    public Integer saveCourse(PsuCourses psuCourses, Integer teacherId) {
        Course course = new Course(null, psuCourses.getCourseName(), psuCourses.getCourseTotalHours(), teacherId);
        Integer courseId = courseService.insert(course);
        return courseId;
    }

    public void saveLesson(PsuCourses psuCourses, Integer courseId, Integer classroomId) {
        Lesson lesson = new Lesson(null, psuCourses.getLessonDatetime(), psuCourses.getLessonHours(), courseId, classroomId);
        lessonService.insert(lesson);
    }
}
