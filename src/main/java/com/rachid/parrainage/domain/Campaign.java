package com.rachid.parrainage.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

/**
 * A Campaign.
 */
@Entity
@Table(name = "campaign")
public class Campaign implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_week")
    private Boolean isWeek;

    @Column(name = "is_month")
    private Boolean isMonth;

    @ManyToMany
    @JoinTable(
        name = "rel_campaign__participant",
        joinColumns = @JoinColumn(name = "campaign_id"),
        inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<User> participants = new HashSet<>();

    @JsonIgnoreProperties(value = { "pictures" }, allowSetters = true)
    @OneToOne
    @JoinColumn(unique = true)
    private Gift gift;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Campaign id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsWeek() {
        return this.isWeek;
    }

    public Campaign isWeek(Boolean isWeek) {
        this.setIsWeek(isWeek);
        return this;
    }

    public void setIsWeek(Boolean isWeek) {
        this.isWeek = isWeek;
    }

    public Boolean getIsMonth() {
        return this.isMonth;
    }

    public Campaign isMonth(Boolean isMonth) {
        this.setIsMonth(isMonth);
        return this;
    }

    public void setIsMonth(Boolean isMonth) {
        this.isMonth = isMonth;
    }

    public Set<User> getParticipants() {
        return this.participants;
    }

    public void setParticipants(Set<User> users) {
        this.participants = users;
    }

    public Campaign participants(Set<User> users) {
        this.setParticipants(users);
        return this;
    }

    public Campaign addParticipant(User user) {
        this.participants.add(user);
        return this;
    }

    public Campaign removeParticipant(User user) {
        this.participants.remove(user);
        return this;
    }

    public Gift getGift() {
        return this.gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Campaign gift(Gift gift) {
        this.setGift(gift);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Campaign)) {
            return false;
        }
        return id != null && id.equals(((Campaign) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Campaign{" +
            "id=" + getId() +
            ", isWeek='" + getIsWeek() + "'" +
            ", isMonth='" + getIsMonth() + "'" +
            "}";
    }
}
