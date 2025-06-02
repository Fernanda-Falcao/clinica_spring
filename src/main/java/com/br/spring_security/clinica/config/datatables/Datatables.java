package com.br.spring_security.clinica.config.datatables;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

// Componente gerenciado pelo Spring, usado para processar requisições do DataTables
@Component
public class Datatables {

    private HttpServletRequest request; // Requisição HTTP que vem do DataTables
    private String[] colunas; // Lista de nomes das colunas da tabela (usada para ordenação)

    public Datatables() {
        super(); // Construtor padrão
    }

    // Monta o JSON esperado pelo DataTables a partir da página de dados retornada pelo Spring Data
    public Map<String, Object> getResponse(Page<?> page) {
        Map<String, Object> json = new LinkedHashMap<>();
        json.put("draw", draw()); // Número da requisição, para controle do DataTables
        json.put("recordsTotal", page.getTotalElements()); // Total de registros sem filtro
        json.put("recordsFiltered", page.getTotalElements()); // Total de registros com filtro (neste caso, igual)
        json.put("data", page.getContent()); // Lista de objetos da página atual
        return json;
    }

    // Getters e setters

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    // Recupera o número da requisição para controle de sincronização
    private int draw() {
        return Integer.parseInt(this.request.getParameter("draw"));
    }

    // Recupera o índice inicial da página (start)
    private int start() {
        return Integer.parseInt(this.request.getParameter("start"));
    }

    // Quantidade de registros por página (length)
    public int getLength() {
        return Integer.parseInt(this.request.getParameter("length"));
    }

    // Calcula o número da página atual com base em start e length
    public int getCurrentPage() {
        return start() / getLength();
    }

    // Retorna o nome da coluna a ser usada na ordenação
    public String getColumnName() {
        int iCol = Integer.parseInt(this.request.getParameter("order[0][column]"));
        return this.colunas[iCol];
    }

    // Retorna a direção da ordenação: ASC ou DESC
    public Sort.Direction getDirection() {
        String order = this.request.getParameter("order[0][dir]");
        if ("desc".equalsIgnoreCase(order)) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }

    // Texto da busca digitada no campo de pesquisa do DataTables
    public String getSearch() {
        return this.request.getParameter("search[value]");
    }

    // Cria e retorna um Pageable com base nos parâmetros da requisição
    public Pageable getPageable() {
        return PageRequest.of(getCurrentPage(), getLength(), getDirection(), getColumnName());
    }
}