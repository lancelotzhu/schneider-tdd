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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.appfuse.model.User;

@Entity
@Table(name = "account")
@NamedQueries({
        @NamedQuery(
                name = "getByUsername",
                query = "select a from Account a,User u where a.user.id = u.id and u.username = :username"
        )
})
public class Account implements Serializable {

	private static final long serialVersionUID = 1427975937738815257L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @Column(nullable = false)
	private BigDecimal balance;
    
    @Column(nullable = false)
	private String status;
    
    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;
    
    @Version
    private Integer version;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
