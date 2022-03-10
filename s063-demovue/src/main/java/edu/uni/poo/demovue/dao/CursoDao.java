package edu.uni.poo.demovue.dao;

import java.util.List;
import java.util.ArrayList;
import edu.uni.poo.demovue.bean.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class CursoDao implements Dao<Curso> {
    @Autowired
    JdbcTemplate template;
    public List<Curso> obtenerTodos(){
        List<Curso> res = new ArrayList<>();
        String sql = "SELECT * FROM CURSO";
        SqlRowSet rs = template.queryForRowSet(sql);
        while(rs.next()){
            res.add(obtenerRegistro(rs));
        }
        return res;
    }

    public void agregar(Curso curso){
        String sql = "INSERT INTO CURSO(NOMBRE, CREDITOS, COSTO) " 
                        + "VALUES(?, ?, ?)";
        template.update(sql, curso.getNombre(), curso.getCreditos(),
            curso.getCosto());
    }

    public Curso buscar(Long id){
        Curso curso = null;
        String sql = "SELECT * FROM CURSO WHERE CODIGO = ?";
        SqlRowSet rs = template.queryForRowSet(sql, id);
        if(rs.next()){
            curso = obtenerRegistro(rs);
        }
        return curso;
    }

    public void actualizar(Curso curso){
        String sql = "UPDATE CURSO SET NOMBRE = ?, CREDITOS = ?,"
                        + "COSTO = ? WHERE CODIGO = ?";
        template.update(sql, curso.getNombre(), curso.getCreditos(), 
                        curso.getCosto(), curso.getCodigo());
    }

    public void eliminar(Curso curso){
        String sql = "DELETE FROM CURSO WHERE CODIGO = ?";
        template.update(sql, curso.getCodigo());
    }

    private Curso obtenerRegistro(SqlRowSet rs){
        Curso curso = new Curso();
        curso.setCodigo(rs.getLong("CODIGO"));
        curso.setNombre(rs.getString("NOMBRE"));
        curso.setCreditos(rs.getInt("CREDITOS"));
        curso.setCosto(rs.getBigDecimal("COSTO"));
        return curso;
    }
}
