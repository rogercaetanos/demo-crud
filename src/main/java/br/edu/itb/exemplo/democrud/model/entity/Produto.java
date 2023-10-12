package br.edu.itb.exemplo.democrud.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.edu.itb.exemplo.democrud.model.enums.EnumTipoProd;

@Entity
@Table(name="Produto")  
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String nome;
	private String descricao;
	private String codigoBarras;
	//private String foto;

	@Column(columnDefinition = "VARBINARY(max)")
	private byte[] foto;
	private double preco;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoProd tipoProd; // ÁGUA, REFRIGERANTE, LANCHE, SALGADO, SALGADINHO, DOCE ou SOBREMESA
	
	private String statusProd;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public EnumTipoProd getTipoProd() {
		return tipoProd;
	}

	public void setTipoProd(EnumTipoProd tipoProd) {
		this.tipoProd = tipoProd;
	}

	public String getStatusProd() {
		return statusProd;
	}

	public void setStatusProd(String statusProd) {
		this.statusProd = statusProd;
	}	
	
	

}
