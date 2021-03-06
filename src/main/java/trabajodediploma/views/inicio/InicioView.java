package trabajodediploma.views.inicio;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import trabajodediploma.data.entity.Estudiante;
import trabajodediploma.data.entity.Trabajador;
import trabajodediploma.data.entity.User;
import trabajodediploma.data.service.EstudianteService;
import trabajodediploma.data.service.TrabajadorService;
import trabajodediploma.security.AuthenticatedUser;
import trabajodediploma.views.MainLayout;
import trabajodediploma.views.footer.MyFooter;
import trabajodediploma.views.login.CrearInformacionPerfilView;

@PageTitle("Inicio")
@Route(value = "inicio", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class InicioView extends Div {

    private CrearInformacionPerfilView crearPerfil;
    private Estudiante estudiante_registrado;
    private Trabajador trabajador_registrado;

    private MyFooter footer;
    private Div content;

    private List<Estudiante> estudiantes;
    private List<Trabajador> trabajadores;

    private Dialog dialog;
    private AuthenticatedUser authenticatedUser;
    private EstudianteService estudianteService;
    private TrabajadorService trabajadorService;
    private Div header;

    public InicioView(
            @Autowired AuthenticatedUser authenticatedUser,
            @Autowired EstudianteService estudianteService,
            @Autowired TrabajadorService trabajadorService
    ) {
        addClassName("inicio-view");
        this.authenticatedUser = authenticatedUser;
        this.estudianteService = estudianteService;
        this.trabajadorService = trabajadorService;
        estudiantes = estudianteService.findAll();
        trabajadores = trabajadorService.findAll();
        Configuracion();

        Optional<User> maybeUser = authenticatedUser.get();
        if (maybeUser.isPresent()) {
            User user = maybeUser.get();

            estudiantes = estudiantes.parallelStream().filter(event -> event.getUser().getUsername().equals(user.getUsername())).collect(Collectors.toList());
            trabajadores = trabajadores.parallelStream().filter(event -> event.getUser().getUsername().equals(user.getUsername())).collect(Collectors.toList());

            if (estudiantes.size() == 0 && trabajadores.size() == 0) {
                dialog.open();
                add(content, Links(), footer);
            } else {
                add(content, Links(), footer);
            }

        } else {
            add(content, Links(), footer);
        }
    }

    private void Configuracion() {
        content = new Div();
        content.addClassName("div-content");
        footer = new MyFooter();
        footer.addClassName("footer");

        /*Header crear  perfil usuario*/
        Button closeButton = new Button(new Icon("lumo", "cross"), (e) -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        Span title = new Span("Perfil");
        Div titleDiv = new Div(title);
        titleDiv.addClassName("div-perfil-title");
        Div buttonDiv = new Div(closeButton);
        buttonDiv.addClassName("div-perfil-button");
        header = new Div(titleDiv, buttonDiv);
        header.addClassName("div-perfil-header");
        /*Fin -> Header crear perfil usuario*/

        /*crear perfil*/
        crearPerfil = new CrearInformacionPerfilView();
        dialog = new Dialog(header, crearPerfil);
        /*Fin -> crear perfil*/
        
        divDescripcionSistema();

    }

    private void divDescripcionSistema() {
        /*titulo*/
        H1 title = new H1("SISTEMA DE CONTROL Y DISTRIBUCI??N DE RECURSOS MATERIALES");
        title.addClassName("h1-title");
        Div divTitle = new Div(title);
        divTitle.addClassName("div-h1-title");
        /*titulo*/

         /*imagen*/
        Image img = new Image("images/presentacion.png", "Logo");
        img.addClassName("image");
        Div imageDiv = new Div(img);
        imageDiv.addClassName("div-image");
        /*imagen*/
        /*descripcion*/
        Paragraph info = new Paragraph("Aplicaci??n destinada al cuidadado, protecci??n y "
                + "conservaci??n de los recursos materiales que posee la facutad 4, sobre la base"
                + "de un uso adecuado en funci??n del control de la entrega del m??dulos a estudiantes "
                + "y trabajadores adem??s de la entrada y salida de libros del almac??n.");
        info.addClassName("info");
        Div divInfo = new Div(imageDiv,info);
        divInfo.addClassName("div-info");
        /*descripcion*/

        content.add(divTitle,divInfo);
      }

    private Component Links() {

        HorizontalLayout layout = new HorizontalLayout();

        H2 sitiosInteres = new H2("SITIOS DE INTER??S");
        HorizontalLayout link1 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("Repositorio Institucional"));
        link1.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor repositorioInstitucional = new Anchor("https://repositorio.uci.cu/jspui/", link1);
        HorizontalLayout link2 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("Biblioteca"));
        link2.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor biblioteca = new Anchor("https://biblioteca.uci.cu/", link2);
        HorizontalLayout link3 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("Internos"));
        link3.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor internos = new Anchor("https://internos.uci.cu/", link3);
        VerticalLayout sitiosDeInteres = new VerticalLayout(sitiosInteres, repositorioInstitucional, biblioteca, internos);

        H2 otroSitios = new H2("OTROS SITIOS");
        HorizontalLayout link4 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("Portal UCI"));
        link4.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor portalUCI = new Anchor("https://www.uci.cu/", link4);
        HorizontalLayout link5 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("Intranet"));
        link5.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor intranet = new Anchor("https://intranet.uci.cu/", link5);
        HorizontalLayout link6 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("Peri??dico Mella"));
        link6.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor periodicoMella = new Anchor("https://periodico.uci.cu/", link6);
        VerticalLayout otrosSitios = new VerticalLayout(otroSitios, portalUCI, intranet, periodicoMella);

        H2 comunidadessUCI = new H2("COMUNIDADES UCI");
        HorizontalLayout link7 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("HumanOS"));
        link7.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor humanos = new Anchor("https://humanos.uci.cu/", link7);
        HorizontalLayout link8 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("FirefoxMan??a"));
        link8.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor fireFoxMania = new Anchor("https://firefoxmania.uci.cu/", link8);
        HorizontalLayout link9 = new HorizontalLayout(VaadinIcon.LINK.create(), new H3("iBlog"));
        link9.setAlignItems(FlexComponent.Alignment.BASELINE);
        Anchor blog = new Anchor("https://iblog.uci.cu/", link9);
        VerticalLayout comunidadesUCI = new VerticalLayout(comunidadessUCI, humanos, fireFoxMania, blog);

        layout.add(sitiosDeInteres, otrosSitios, comunidadesUCI);
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        layout.addClassName("div-link");
        layout.setWidthFull();

        return layout;
    }

}
