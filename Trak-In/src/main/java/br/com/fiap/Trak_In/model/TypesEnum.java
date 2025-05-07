package br.com.fiap.Trak_In.model;

public class TypesEnum {
    
    public enum StatusMoto {
        DISPONIVEL, EM_MANUTENCAO, ALUGADA
    }
    
    public enum StatusCamera {
        ATIVO, INATIVO, MANUTENCAO
    }
    
    public enum StatusLocalizacao {
        ESTACIONADA, EM_MOVIMENTO
    }
    
    public enum FonteDados {
        RFID, VISAO_COMPUTACIONAL, FUSAO, MANUAL
    }
    
    public enum PerfilUsuario {
        ADMIN, OPERADOR, GERENTE
    }
    
    public enum FonteEvento {
        SISTEMA, MANUAL, VISAO_COMPUTACIONAL, RFID
    }
    
    
}
