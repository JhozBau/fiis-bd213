package edu.uni.poo.demovue.dao;

import edu.uni.poo.demovue.bean.Alumno;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class AlumnoDao implements Dao<Alumno>{
    @Autowired
    JdbcTemplate template;
    public List<Alumno> obtenerTodos(){
        List<Alumno> res = new ArrayList<>();
        String sql = "SELECT * FROM ALUMNO";
        SqlRowSet rs = template.queryForRowSet(sql);
        while(rs.next()){
            res.add(obtenerRegistro(rs));
        }
        return res;
    }

    public void agregar(Alumno alumno){
        String sql = "INSERT INTO ALUMNO(NOMBRE, PROMEDIO, CREDITO_DISPONIBLE, "
                        + "FECHA_NACIMIENTO) VALUES(?, ?, ?, ?)";
        template.update(sql, alumno.getNombre(), alumno.getPromedio(),
            alumno.getCreditoDisponible(), alumno.getFechaNacimiento());
    }

    public Alumno buscar(Long id){
        Alumno alumno = null;
        String sql = "SELECT * FROM ALUMNO WHERE CODIGO = ?";
        SqlRowSet rs = template.queryForRowSet(sql, id);
        if(rs.next()){
            alumno = obtenerRegistro(rs);
        }
        return alumno;
    }

    public void actualizar(Alumno alumno){
        String sql = "UPDATE ALUMNO SET NOMBRE = ?, PROMEDIO = ?,"
                        + "CREDITO_DISPONIBLE = ?, FECHA_NACIMIENTO = ?"
                        + " WHERE CODIGO = ? ";
        template.update(sql, alumno.getNombre(), alumno.getPromedio(), 
                        alumno.getCreditoDisponible(), alumno.getFechaNacimiento(), 
                        alumno.getCodigo());
    }

    public void eliminar(Alumno alumno){
        String sql = "DELETE FROM ALUMNO WHERE CODIGO = ?";
        template.update(sql, alumno.getCodigo());
    }

    private Alumno obtenerRegistro(SqlRowSet rs){
        Alumno alumno = new Alumno();
        alumno.setCodigo(rs.getLong("CODIGO"));
        alumno.setNombre(rs.getString("NOMBRE"));
        alumno.setPromedio(rs.getBigDecimal("PROMEDIO"));
        alumno.setCreditoDisponible(rs.getBigDecimal("CREDITO_DISPONIBLE"));
        if(rs.getDate("FECHA_NACIMIENTO") != null){
            alumno.setFechaNacimiento(rs.getDate("FECHA_NACIMIENTO").toLocalDate());
        }        
        return alumno;
    }
    
}
