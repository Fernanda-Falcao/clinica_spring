package com.br.spring_security.clinica.config.datatables;

// Classe utilitária para definir os nomes das colunas utilizadas pelo DataTables
public class DatatablesColunas {

    // Colunas da tabela de especialidades
    public static final String[] ESPECIALIDADES = { "id", "titulo" };

    // Colunas da tabela de médicos
    public static final String[] MEDICOS = { "id", "nome", "crm", "dtInscricao", "especialidades" };

    // Colunas da tabela de agendamentos
    public static final String[] AGENDAMENTOS = { "id", "paciente.nome", "dataConsulta", "medico.nome", "especialidade.titulo" };

    // Colunas da tabela de usuários
    public static final String[] USUARIOS = { "id", "email", "ativo", "perfis" };
}