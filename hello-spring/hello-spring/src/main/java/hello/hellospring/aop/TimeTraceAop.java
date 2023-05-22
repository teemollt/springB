package hello.hellospring.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/* Aop?
핵심 로직은 아니지만 공통 관심사인 로직의 경우
반복 작업을 대신해 간편하게 아래와 같이 모든 곳에 적용할 수 있음..
메서드 호출시 중간에 인터셉팅해서 어떤 사이드 로직을 수행할 수 있음. ㄴ
 */
@Aspect // aop 쓰려면 이거 써줘야함 + @Component로 등록해줘도 되지만 config에 직접 스프링 Bean 등록해주는 경우가 더 많음
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") // 적용범위 설정 가능 문서 참고
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }

    }
}
