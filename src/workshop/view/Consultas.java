package workshop.view;

import workshop.controllers.ControllerFunc_J7;
import workshop.controllers.ControllerFunc_J8;
import workshop.model.Departamento;
import workshop.model.Funcionario;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Consultas {

    public static void main(String[] args) throws Exception {
        // ControllerFunc_J7 control_J7 = new ControllerFunc_J7();
        // control_J7.iniciarExpediente();
        ControllerFunc_J8 control_J8 = new ControllerFunc_J8();
        control_J8.imprimirFuncionarios();
    }
}
