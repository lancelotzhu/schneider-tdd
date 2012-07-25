package com.schneider.tdd.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "trade")
@NamedQueries({
        @NamedQuery(
                name = "getAllUncomplete",
                query = "select t from Trade t where t.status = :status"
        )
})
public class Trade implements Serializable {

	private static final long serialVersionUID = -7136801276566908945L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "stock_code", nullable = false)
	private String stockCode;
	
	@Column(name = "stock_name")
	private String stockName;

	@Column(nullable = false)
	private String operation;
	
	@Column(name = "transacted_price")
	private BigDecimal transactedPrice;
	
	@Column(name = "requested_price", nullable = false)
	private BigDecimal requestedPrice;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private String status;
	
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;
    
    @Version
    private Integer version;
    
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public BigDecimal getTransactedPrice() {
		return transactedPrice;
	}

	public void setTransactedPrice(BigDecimal transactedPrice) {
		this.transactedPrice = transactedPrice;
	}

	public BigDecimal getRequestedPrice() {
		return requestedPrice;
	}

	public void setRequestedPrice(BigDecimal requestedPrice) {
		this.requestedPrice = requestedPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
}
