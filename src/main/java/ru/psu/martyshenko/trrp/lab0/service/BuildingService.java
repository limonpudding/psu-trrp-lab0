package ru.psu.martyshenko.trrp.lab0.service;

import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.impl.DefaultConfiguration;
import ru.psu.martyshenko.trrp.lab0.datasources.DataSourcePostgres;
import ru.psu.martyshenko.trrp.lab0.pg.tables.pojos.Building;
import ru.psu.martyshenko.trrp.lab0.pg.tables.daos.BuildingDao;

import java.sql.Connection;
import java.util.List;

import static org.jooq.SQLDialect.POSTGRES;
import static ru.psu.martyshenko.trrp.lab0.pg.tables.Building.BUILDING;

public class BuildingService {

    DSLContext context;
    private BuildingDao dao;

    public BuildingService() {
        Connection connection = DataSourcePostgres.getInstance().getConnection();
        context = DSL.using(connection, POSTGRES);
        dao = new BuildingDao();
        Configuration configuration = new DefaultConfiguration().set(connection).set(POSTGRES);
        dao.setConfiguration(configuration);
    }

    public void insert(Building building) {
        if (!dao.existsById(building.getBuilding())) {
            dao.insert(building);
        }
    }
}
