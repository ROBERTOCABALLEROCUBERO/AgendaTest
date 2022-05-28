/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ed.agenda.daoTest;

import ed.agenda.dao.AgendaDaoArrayList;
import ed.agenda.entidades.Contacto;
import ed.agenda.entidades.ContactoEmpresa;
import ed.agenda.entidades.ContactoPersona;
import java.lang.reflect.Array;
import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import org.junit.*;

/**
 *
 * @author marco
 */
public class TestAgendaDaoArrayList {

    AgendaDaoArrayList dao = new AgendaDaoArrayList();

    @After
    public void vaciarArray() {
        dao.setContactos(new ArrayList<Contacto>());

    }

    @Test
    public void TestCrearContactoPersona() {

        ContactoPersona CP1 = new ContactoPersona("11221999", "BillGates", "+34 981 666666");

        //Verifico que la agenda esta vacia
        assertTrue(dao.getContactos().isEmpty());
        //Añado el cotnacto persona
        dao.crearContactoPersona(CP1);

        //Verifico que la agenda contiene el contacto que yo he añadido
//        assertTrue(dao.getContactos().contains(CP1));
        Assert.assertEquals(CP1, dao.getContactos().get(0));

        //Verificao que la agenda a aumentado su catidad de cotnactos en 1
        assertTrue(dao.getContactos().size() == 1);

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void obtenerContactoPorPosicionNegativa() {

        dao.obtenerContactoPorPosicion(-1);
    }

    @Test
    public void obtenerContactoCorrecta() {
        //Verifico que la agenda esta vacia
        assertTrue(dao.getContactos().isEmpty());

        ContactoPersona CP1 = new ContactoPersona("11221999", "BillGates", "+34 981 666666");

        ContactoPersona CP2 = new ContactoPersona("11221999", "SteveJobs", "+34 981 666666");
        dao.crearContactoPersona(CP1);
        dao.crearContactoPersona(CP2);

        ContactoPersona resultado = (ContactoPersona) dao.obtenerContactoPorPosicion(0);

        assertEquals(CP1, resultado);

    }

    @Test
    public void anadircontactoempresa() {

        assertTrue(dao.getContactos().isEmpty());
        ContactoEmpresa CE1 = new ContactoEmpresa(" www.vodafone", "Vodafone", "14873741");
        dao.crearContactoEmpresa(CE1);
        ContactoEmpresa resultado = (ContactoEmpresa) dao.obtenerContactoPorPosicion(0);
        assertEquals(resultado, dao.getContactos());
    }

    @Test
    public void anadircontactoempresamal() {
        assertTrue(dao.getContactos().isEmpty());
        ContactoEmpresa CE1 = new ContactoEmpresa(" www.vodafone", "AÑÑÑÑ^^$$##¿?.", "14873741");
        dao.crearContactoEmpresa(CE1);
        ContactoEmpresa resultado = (ContactoEmpresa) dao.obtenerContactoPorPosicion(0);
        assertNotEquals(resultado, CE1);

    }

    public void anadirtrabajadorempresa() {
        assertTrue(dao.getContactos().isEmpty());
        ContactoEmpresa CE1 = new ContactoEmpresa(" www.vodafone", "Vodafone", "14873741");
        dao.crearContactoEmpresa(CE1);
        ContactoPersona CP1 = new ContactoPersona("11221999", "BillGates", "+34 981 666666");
        ArrayList trabajadores = new ArrayList<ContactoPersona>();
        trabajadores.add(CP1);
        CE1.setTrabajadores(trabajadores);
        assertEquals(trabajadores, CE1.getTrabajadores());

    }

}
