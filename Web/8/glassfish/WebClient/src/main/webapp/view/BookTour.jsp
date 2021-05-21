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
                <li><a href="./index.html" title="">Home</a></li>
                <li><a href="./about.html" title="">About</a></li>
                <li><a href="./BookTour.jsp" title="">Book a tour</a></li>
                <li><a href="contact.jsp" title="">Contact</a></li>

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

    <h2 class="template-title-example">Book a tour</h2>
    <p>Insert your info then press "Book" button. After that our specialist will contact you to continue booking process.</p>

    <form id="testId" class="reveal-content">
    </form>


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
<script src="helpMethods.js"></script>

<script>
    generateForm("testId",FormBook);
</script>
</body>

</html>