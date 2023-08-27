package com.bibimbap.DailyPlanServer.domain.category.entity;

import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import com.bibimbap.DailyPlanServer.domain.toDo.entity.ToDo;
import com.bibimbap.DailyPlanServer.global.error.ErrorCode;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryCode categoryCode;

    private int countByToDo;

    private int successToDoCount;

    @OneToMany(mappedBy = "category")
    private List<ToDo> toDoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Category(CategoryCode categoryCode, int countByToDo, int successToDoCount, Member member){
        this.categoryCode = categoryCode;
        this.countByToDo = countByToDo;
        this.successToDoCount = successToDoCount;
        this.member = member;
    }

    public void plusCountByToDo(){
        this.countByToDo += 1;
    }

    public void minusCountByToDo(){
        if(this.countByToDo <= 0)
            System.out.println("해당 카테고리의 count는 이미 0 입니다 : " + this.categoryCode.getTitle());
        else
            this.countByToDo -= 1;
    }

    public void completeToDo(){
        this.successToDoCount += 1;
    }

    public void cancelCompleteToDo(){
        if(this.successToDoCount <= 0)
            System.out.println("해당 카테고리의 successCount는 이미 0 입니다 : " + this.categoryCode.getTitle());
        else
            this.successToDoCount -= 1;
    }

    public void setMember(Member member){
        this.member = member;
    }
}
