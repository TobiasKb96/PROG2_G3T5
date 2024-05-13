package at.ac.fhcampuswien.fhmdb.ui;
@FunctionalInterface
public interface ClickHandler<C> {
    void onClick(C c);
}
