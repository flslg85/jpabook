package jpabook.start;

import javax.persistence.*;

/**
 * Created by flslg85 on 2015. 11. 12..
 */
@Entity
public class Board {
    @Id
    /**
     * IDENTITY
     * MySQL, PostgreSQL, SQL Server, DB2 에서 사용
     */
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    /**
     * SEQUENCE
     * 오라클, PostgreSQL, DB2, H2 에서 사용
     * @SequenceGenerator를 사용해서 BOARD_SEQ_GENERATOR 시퀀스 생성기를 등록
     */
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,   // 키 생성 전략을 설정
//            generator = "BOARD_SEQ_GENERATOR"     // 등록한 시퀀스 생성기를 선택
//    )
//    @SequenceGenerator(
//        name = "BOARD_SEQ_GENERATOR",
//        sequenceName = "BOARD_SEQ", // 매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1,
//        allocationSize = 1
//)

    /**
     * TABLE
     * @TableGenerator 를 사용해서 키 생성기를 등록하고 MY_SEQUENCES 테이블을 키 생성용 테이블로 매핑
     */
//    @TableGenerator(
//            name = "BOARD_SEQ_GENERATOR",
//            table = "MY_SEQUENCES",
//            pkColumnValue = "BOARD_SEQ",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.TABLE,
//            generator = "BOARD_SEQ_GENERATOR"
//    )

    /**
     * AUTO
     * @GeneratedValue.strategy : 기본값이 AUTO 이기 떄문에 @GeneratedValue 만 쓰면 AUTO 로 적용됨
     */
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue
    private Long id;

    private String data;

    public Long getId() {
        return id;
    }

    public void setData(String data) {
        this.data = data;
    }
}
