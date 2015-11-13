package jpabook.start;

import javax.persistence.*;

/**
 * Created by flslg85 on 2015. 11. 12..
 */
@Entity
/**
 * sequence
 */
//@SequenceGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        sequenceName = "BOARD_SEQ", // 매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1,
//        allocationSize = 1
//)
public class Board {
    @Id
    /**
     * identity
     * MySQL, PostgreSQL, SQL Server, DB2 에서 사용
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * sequence
     * 오라클, PostgreSQL, DB2, H2 에서 사용
     */
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "BOARD_SEQ_GENERATOR"
//    )
    private Long id;

    private String data;

    public Long getId() {
        return id;
    }

    public void setData(String data) {
        this.data = data;
    }
}
