/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bangmaple.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bangmaple
 */
@Entity
@Table(name = "rules",catalog = "login", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"rule_name"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rules.findAll", query = "SELECT r FROM Rules r"),
    @NamedQuery(name = "Rules.findById", query = "SELECT r FROM Rules r WHERE r.id = :id"),
    @NamedQuery(name = "Rules.findByRuleName", query = "SELECT r FROM Rules r WHERE r.ruleName = :ruleName"),
    @NamedQuery(name = "Rules.findByRuleDescription", query = "SELECT r FROM Rules r WHERE r.ruleDescription = :ruleDescription")})
public class Rules implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "rule_name", nullable = false, length = 250)
    private String ruleName;
    
    @Size(max = 500)
    @Column(name = "rule_description", length = 500)
    private String ruleDescription;
  
    @Lob
    @Column(name = "rule_file")
    private byte[] ruleFile;

    public Rules() {
    }

    public Rules(Integer id) {
        this.id = id;
    }

    public Rules(Integer id, String ruleName) {
        this.id = id;
        this.ruleName = ruleName;
    }
    
    public Rules(Integer id, String ruleName, String ruleDescription) {
        this.id = id;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public byte[] getRuleFile() {
        return ruleFile;
    }

    public void setRuleFile(byte[] ruleFile) {
        this.ruleFile = ruleFile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rules)) {
            return false;
        }
        Rules other = (Rules) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bangmaple.entities.Rules[ id=" + id + " ]";
    }
    
}
