package edu.uni.poo.demovue.bean;

import java.math.BigDecimal;

public class Matricula {
    Long codigoAlumno;
    Long codigoCurso;
    BigDecimal nota;

    public Long getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(Long codAlumno) {
        this.codigoAlumno = codAlumno;
    }

    public Long getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(Long codCurso) {
        this.codigoCurso = codCurso;
    }

    public BigDecimal getNota() {
        return nota;
    }

    public void setNota(BigDecimal nota) {
        this.nota = nota;
    }

}
