package com.example.application.views;

import com.example.application.views.alunoform.AlunoFormView;
import com.example.application.views.itemrubricaform.ItemRubricaFormView;
import com.example.application.views.notasgrid.NotasGridView;
import com.example.application.views.rubricaform.RubricaFormView;
import com.example.application.views.rubricas.RubricasView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Rub");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Rubricas", RubricasView.class, LineAwesomeIcon.FILE.create()));
        nav.addItem(new SideNavItem("Aluno Form", AlunoFormView.class, LineAwesomeIcon.USER.create()));
        nav.addItem(new SideNavItem("Rubrica Form", RubricaFormView.class, LineAwesomeIcon.BOOK_READER_SOLID.create()));
        nav.addItem(new SideNavItem("Item Rubrica Form", ItemRubricaFormView.class,
                LineAwesomeIcon.PENCIL_ALT_SOLID.create()));
        nav.addItem(new SideNavItem("Notas Grid", NotasGridView.class, LineAwesomeIcon.FILTER_SOLID.create()));

        return nav;
    }

    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
