<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="design/Header.jsp"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Accueil</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>


<body class="bg-gray-200">
<div class="container mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold text-center mb-4">Bienvenue sur Reverso !</h1>
    <p class="text-lg text-center text-gray-700 mb-8">Un outil de gestion de vos clients et prospects.</p>
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <div class="bg-white p-4 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold mb-2">Section 1</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed consequat justo eu odio suscipit, nec
                fermentum urna dignissim.</p>
        </div>
        <div class="bg-white p-4 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold mb-2">Section 2</h2>
            <p>Integer posuere nunc sit amet tortor posuere, nec blandit leo consequat. Duis eget dolor sem.</p>
        </div>
        <div class="bg-white p-4 rounded-lg shadow-md">
            <h2 class="text-xl font-semibold mb-2">Section 3</h2>
            <p>Curabitur laoreet orci a sapien commodo, sed blandit ex lobortis. Nulla auctor eros a magna
                molestie.</p>
        </div>
    </div>
</div>
</body>
<%@ include file="design/Footer.jsp"%>
</html>
