package com.alex.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PackageEntity {
    @Id
    @SequenceGenerator(name="package_gen", sequenceName="package_seq")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="package_gen")
    private long id;
    private String name;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name="parent_package_id")
    private PackageEntity parentPackage;

    @OneToMany(mappedBy="taskPackage")
    private List<TaskEntity> taskChildren;
    @OneToMany(mappedBy="parentPackage")
    private List<PackageEntity> packageChildren;

    public PackageEntity(String name,
                         PackageEntity parentPackage,
                         List<TaskEntity> taskChildren,
                         List<PackageEntity> packageChildren) {
        this.name = name;
        this.parentPackage = parentPackage;
        this.taskChildren = taskChildren;
        this.packageChildren = packageChildren;
    }

    public PackageEntity() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PackageEntity getParentPackage() {
        return parentPackage;
    }

    public void setParentPackage(PackageEntity parentPackage) {
        this.parentPackage = parentPackage;
    }

    public List<TaskEntity> getTaskChildren() {
        return taskChildren;
    }

    public void setTaskChildren(List<TaskEntity> taskChildren) {
        this.taskChildren = taskChildren;
    }

    public List<PackageEntity> getPackageChildren() {
        return packageChildren;
    }

    public void setPackageChildren(List<PackageEntity> packageChildren) {
        this.packageChildren = packageChildren;
    }
}
