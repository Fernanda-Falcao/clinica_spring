-- Criação do banco (opcional se você já tiver um banco criado)
CREATE DATABASE clinica_db;

-- Seleciona o banco (PostgreSQL usa conexão direta, esse comando é desnecessário)

-- Tabela: especialidades
DROP TABLE IF EXISTS especialidades CASCADE;

CREATE TABLE especialidades (
  id BIGSERIAL PRIMARY KEY,
  descricao TEXT,
  titulo VARCHAR(255) NOT NULL UNIQUE
);

-- Tabela: horas
DROP TABLE IF EXISTS horas CASCADE;

CREATE TABLE horas (
  id BIGSERIAL PRIMARY KEY,
  hora_minuto TIME NOT NULL UNIQUE
);

INSERT INTO horas (hora_minuto) VALUES 
('07:00:00'), ('07:30:00'), ('08:00:00'), ('08:30:00'), 
('09:00:00'), ('09:30:00'), ('10:00:00'), ('10:30:00'), 
('11:00:00'), ('11:30:00'), ('13:00:00'), ('13:30:00'), 
('14:00:00'), ('14:30:00'), ('15:00:00'), ('15:30:00'), 
('16:00:00'), ('16:30:00'), ('17:00:00'), ('17:30:00');

-- Tabela: usuarios
DROP TABLE IF EXISTS usuarios CASCADE;

CREATE TABLE usuarios (
  id BIGSERIAL PRIMARY KEY,
  ativo BOOLEAN NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  codigo_verificador VARCHAR(6)
);

-- Tabela: perfis
DROP TABLE IF EXISTS perfis CASCADE;

CREATE TABLE perfis (
  id BIGSERIAL PRIMARY KEY,
  descricao VARCHAR(255) NOT NULL UNIQUE
);

INSERT INTO perfis (descricao) VALUES 
('ADMIN'), ('MEDICO'), ('PACIENTE');

-- Tabela: medicos
DROP TABLE IF EXISTS medicos CASCADE;

CREATE TABLE medicos (
  id BIGSERIAL PRIMARY KEY,
  crm INTEGER NOT NULL UNIQUE,
  data_inscricao DATE NOT NULL,
  nome VARCHAR(255) NOT NULL UNIQUE,
  id_usuario BIGINT,
  CONSTRAINT fk_medico_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- Tabela: pacientes
DROP TABLE IF EXISTS pacientes CASCADE;

CREATE TABLE pacientes (
  id BIGSERIAL PRIMARY KEY,
  data_nascimento DATE NOT NULL,
  nome VARCHAR(255) NOT NULL UNIQUE,
  id_usuario BIGINT,
  CONSTRAINT fk_paciente_usuario FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

-- Tabela: agendamentos
DROP TABLE IF EXISTS agendamentos CASCADE;

CREATE TABLE agendamentos (
  id BIGSERIAL PRIMARY KEY,
  data_consulta DATE,
  id_especialidade BIGINT,
  id_horario BIGINT,
  id_medico BIGINT,
  id_paciente BIGINT,
  CONSTRAINT fk_especialidade FOREIGN KEY (id_especialidade) REFERENCES especialidades(id),
  CONSTRAINT fk_horario FOREIGN KEY (id_horario) REFERENCES horas(id),
  CONSTRAINT fk_medico FOREIGN KEY (id_medico) REFERENCES medicos(id),
  CONSTRAINT fk_paciente FOREIGN KEY (id_paciente) REFERENCES pacientes(id)
);

-- Tabela: medicos_tem_especialidades
DROP TABLE IF EXISTS medicos_tem_especialidades CASCADE;

CREATE TABLE medicos_tem_especialidades (
  id_especialidade BIGINT NOT NULL,
  id_medico BIGINT NOT NULL,
  CONSTRAINT pk_medico_especialidade PRIMARY KEY (id_especialidade, id_medico),
  CONSTRAINT fk_me_especialidade FOREIGN KEY (id_especialidade) REFERENCES especialidades(id),
  CONSTRAINT fk_me_medico FOREIGN KEY (id_medico) REFERENCES medicos(id)
);

-- Tabela: usuarios_tem_perfis
DROP TABLE IF EXISTS usuarios_tem_perfis CASCADE;

CREATE TABLE usuarios_tem_perfis (
  usuario_id BIGINT NOT NULL,
  perfil_id BIGINT NOT NULL,
  PRIMARY KEY (usuario_id, perfil_id),
  CONSTRAINT fk_usuario_perfil_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
  CONSTRAINT fk_usuario_perfil_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id)
);
