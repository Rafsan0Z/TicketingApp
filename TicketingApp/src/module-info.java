/**
 *
 */
/**
 *
 */
module TicketingApp {
    requires java.desktop;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.annotation;
    opens ticketingApp.data.dto to com.fasterxml.jackson.databind;
}