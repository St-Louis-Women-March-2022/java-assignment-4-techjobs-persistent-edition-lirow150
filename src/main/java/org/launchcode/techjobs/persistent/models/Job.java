package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;


//Since the Job model class has id and name fields, it too can inherit from AbstractEntity. Update the class definition of Job to extend AbstractEntity. Remove the redundant fields from Job.
//Replace the type of the field employer to be of type Employer. You will also need to refactor the affected constructor and getter and setter that use this field.
//Add the @ManyToOne annotation on the field employer
@Entity
public class Job extends AbstractEntity {

    @ManyToOne
    private Employer employer;


    @ManyToMany
    @NotNull(message = "Must choose a skill to continue.")
    private List<Skill> skills;

    public Job() {
    }

    public Job(Employer anEmployer, List<Skill> someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}