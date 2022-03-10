package edu.uni.poo.demovue.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import edu.uni.poo.demovue.bean.Matricula;

@Repository
public class MatriculaDao {
    @Autowired
    JdbcTemplate template;
    public void agregar(Matricula matricula){
        String sql = "INSERT INTO MATRICULA VALUES(?, ?)";
        template.update(sql, matricula.getCodigoAlumno(), matricula.getCodigoCurso());
    }
}
