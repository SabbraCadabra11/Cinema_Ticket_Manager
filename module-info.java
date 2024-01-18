module cinema.ticket.manager {
    requires spring.boot.starter.data.jpa;
    requires spring.boot.starter.web;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires spring.boot.starter.thymeleaf;
    requires io.github.wimdeblauwe.htmx.spring.boot.thymeleaf;
    requires lombok;
    requires spring.boot.devtools;
    requires com.google.zxing.core;
    requires com.google.zxing.javase;
    requires h2;
    requires org.projectlombok;

    exports dw.cinema_ticket_manager;
}