<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width,initial-scale=1" name="viewport">
    <meta content="Iv.Org travel official site" name="description">
    <meta name="google" content="notranslate"/>

    <!-- Disable tap highlight on IE -->
    <meta name="msapplication-tap-highlight" content="no">

    <link href="./assets/apple-icon-180x180.png" rel="apple-touch-icon">
    <link href="./assets/favicon.ico" rel="icon">


    <title>Title page</title>

    <link href="./main.82cfd66e.css" rel="stylesheet">
</head>

<body>

<!-- Add your content of header -->
<header class="">
    <div class="navbar navbar-default visible-xs">
        <button type="button" class="navbar-toggle collapsed">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a href="./index.html" class="navbar-brand">Mashup Template</a>
    </div>

    <nav class="sidebar">
        <div class="navbar-collapse" id="navbar-collapse">
            <div class="site-header hidden-xs">
                <a class="site-brand" title="">
                    <img class="img-responsive site-logo" alt="" src="./assets/images/mashup-logo.svg">
                    Iv.Org
                </a>
                <p style="font-family:'Bradley Hand ITC'; font-size: 20px; color: black">Jobs fill your pockets,
                    adventures fill your soul...</p>
            </div>
            <ul class="nav">
                <li><a href="./index.jsp" title="">Home</a></li>
                <li><a href="./about.jsp" title="">About</a></li>
                <li><a href="./contact.jsp" title="">Contact</a></li>
                <li><a href="${pageContext.request.contextPath}/superServ?commandId=clientsInfo" title="">Clients
                    info</a></li>
                <li><a href="clientToursPage.jsp">Client tours</a></li>
                <li><a href="clientBookToursPage.jsp">Book a tour</a></li>
                <li><a href="UpdateDiscountPage.jsp">Update discount</a></li>

            </ul>

            <nav class="nav-footer">
                <p class="nav-footer-social-buttons">
                    <a class="fa-icon" href="https://www.instagram.com/vanya_shishkan/?hl=ru" title="">
                        <i class="fa fa-instagram"></i>
                    </a>
                </p>
                <p>© Iv.Org | Tourism organisation </p>
            </nav>
        </div>
    </nav>
</header>
<main class="" id="main-collapse">


    <div class="row">
        <div class="col-xs-12">
            <div class="section-container-spacer">
                <h1>Contact</h1>
                <p>In case you have any questions you can contact our support directly via email or given number.
                    You can also send a message using form below and we will respond you as soon as possible.</p>
            </div>
            <div class="section-container-spacer">
                <form id="contactForm" class="reveal-content">
                </form>
            </div>
        </div>
    </div>


</main>

<script>
    document.addEventListener("DOMContentLoaded", function (event) {
        navbarToggleSidebar();
        navActivePage();
    });
</script>

<!-- Google Analytics: change UA-XXXXX-X to be your site's ID 

<script>
  (function (i, s, o, g, r, a, m) {
    i['GoogleAnalyticsObject'] = r; i[r] = i[r] || function () {
      (i[r].q = i[r].q || []).push(arguments)
    }, i[r].l = 1 * new Date(); a = s.createElement(o),
      m = s.getElementsByTagName(o)[0]; a.async = 1; a.src = g; m.parentNode.insertBefore(a, m)
  })(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');
  ga('create', 'UA-XXXXX-X', 'auto');
  ga('send', 'pageview');
</script>

-->
<script type="text/javascript" src="./main.85741bff.js"></script>
</body>

<script src="helpMethods.js">
</script>

<script>
    generateForm("contactForm", FormContact);
</script>
</html>