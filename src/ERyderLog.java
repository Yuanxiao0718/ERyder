import java.time.LocalDateTime;

public class ERyderLog {
//    • log（String类型）– 该字符串仅存储日志条目的 ID
//• event（String类型）– 该字符串存储事件的简要描述
//• timeStamp（LocalDateTime 类型）– 该对象存储日志创建的时间，通常是在事件发生后立即创建
    private  String log;
    private  String event;
    private LocalDateTime timeStamp;
    public ERyderLog(String log, String event, LocalDateTime timeStamp) {
        this.log = log;
        this.event = event;
        this.timeStamp = timeStamp;
    }

    public String getLog() {
        return log;
    }

    public String getEvent() {
        return event;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
//    BR156 – Bike with bikeID was rented by from location at 2026-04-03T10:20
    @Override
    public String toString() {
        return log + "–" +
                event + '\'' +
               timeStamp ;
    }
}
