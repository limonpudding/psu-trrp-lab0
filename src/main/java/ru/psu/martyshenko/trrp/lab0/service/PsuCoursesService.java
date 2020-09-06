package ru.psu.martyshenko.trrp.lab0.service;

import static org.jooq.SQLDialect.*;

import org.jooq.*;
import org.jooq.impl.*;
import ru.psu.martyshenko.trrp.lab0.datasources.DataSourceFireBird;
import ru.psu.martyshenko.trrp.lab0.fb.tables.pojos.PsuCourses;

import java.util.List;

import static ru.psu.martyshenko.trrp.lab0.fb.tables.PsuCourses.PSU_COURSES;

public class PsuCoursesService {

    DSLContext context;

    public PsuCoursesService() {
        context = DSL.using(DataSourceFireBird.getInstance().getConnection(), FIREBIRD);
    }

    public List<PsuCourses> getAllCourses() {
        return context.selectFrom(PSU_COURSES).fetchInto(PsuCourses.class);
    }
}
