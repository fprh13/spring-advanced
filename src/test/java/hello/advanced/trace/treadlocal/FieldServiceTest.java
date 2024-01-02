package hello.advanced.trace.treadlocal;

import hello.advanced.trace.treadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() {
        log.info("main start");

//        Runnable user = new Runnable() {
//            @Override
//            public void run() {
//                fieldService.logic("user");
//            }
//        };

        // -> 람다 버젼으로 테스트

        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
        sleep(2000); // 동시성 문제 발생 X

//        sleep(100); // 동시성 문제 발생 O
//        01:29:24.475 [thread-A] INFO hello.advanced.trace.treadlocal.code.FieldService -- 저장 name=userA -> nameStore=null
//        01:29:24.580 [thread-B] INFO hello.advanced.trace.treadlocal.code.FieldService -- 저장 name=userB -> nameStore=userA
//        01:29:25.486 [thread-A] INFO hello.advanced.trace.treadlocal.code.FieldService -- 조회 nmaeStore=userB
//        01:29:25.586 [thread-B] INFO hello.advanced.trace.treadlocal.code.FieldService -- 조회 nmaeStore=userB
//        01:29:27.585 [Test worker] INFO hello.advanced.trace.treadlocal.FieldServiceTest -- main exit

        threadB.start();

        sleep(3000); // 메인 쓰레드 종료 대기
        log.info("main exit");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
