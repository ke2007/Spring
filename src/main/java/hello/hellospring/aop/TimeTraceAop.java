package hello.hellospring.aop;

import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimeTraceAop {

   @Around("execution(* hello.hellospring..*(..))") //타겟팅가능 패키지명..거기밑에있는거..클래스명..파라미터타입
    public Object execute(ProceedingJoinPoint joinPoint)throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("joinPoint = " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END = " + joinPoint.toString() + " " + timeMs + "Ms");
        }
    }
}
