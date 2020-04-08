package by.jwd.lemesheuski.hostel.controller.router;

public class Router {
    private final String route;
    private final RouterType type;

    public Router(String route, RouterType type) {
        this.route = route;
        this.type = type;
    }

    public String getRoute() {
        return route;
    }

    public RouterType getType() {
        return type;
    }
}
