package com.init.products.entitys;

import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "techForUSer")
@Where(clause = "active = 1")
public class TechnologiesForUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "active", columnDefinition = "int default 1")
    private Integer active;

    @Column(name = "percentage", nullable = false)
    private int percentage;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private Members members;

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public void setMembers(Members members) {
        this.members = members;
    }

    public int getPercentage() {
        return percentage;
    }

    public Members getMembers() {
        return members;
    }
}
