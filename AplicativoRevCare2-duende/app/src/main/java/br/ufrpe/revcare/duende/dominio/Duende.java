package br.ufrpe.revcare.duende.dominio;

public class Duende {
        private long id;
        private String nome;
        private String cpf;
        private String altura;
        private String email;
        private String senha;

        public String getAltura() {
            return altura;
        }

        public void setAltura(String altura) {
            this.altura = altura;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            this.cpf = cpf;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

}
