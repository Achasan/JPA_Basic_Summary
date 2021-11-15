package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (요구사항)
 * 1. 회원은 일반 회원과 관리자로 구분해야한다.
 * 2. 회원 가입일과 수정일이 있어야 한다.
 * 3. 회원을 설명할 수 있는 필드가 있어야 한다. 이 필드는 길이 제한이 없다.
 */
@Entity
@TableGenerator(name = "member_seq_generator", table = "my_sequences", pkColumnValue = "member_seq", allocationSize = 1)
public class Member2 {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "member_seq_generator")
    private Long id;

    // 필드변수는 username이지만 DB컬럼에는 name으로 저장하고 싶을 때 작성한다.
    @Column(name = "name")
    private String username;

    private Integer age;

    // enum class RoleType으로 회원등급설정 : @Enumerated 사용용
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public LocalDate getTestLocalDate() {
        return testLocalDate;
    }

    public void setTestLocalDate(LocalDate testLocalDate) {
        this.testLocalDate = testLocalDate;
    }

    public LocalDateTime getTestLocalDateTime() {
        return testLocalDateTime;
    }

    public void setTestLocalDateTime(LocalDateTime testLocalDateTime) {
        this.testLocalDateTime = testLocalDateTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
