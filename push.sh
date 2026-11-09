#!/bin/bash
# Script para subir o projeto EcoFleet no GitHub
# Execute este script dentro da pasta do projeto

set -e

echo "🌿 EcoFleet — Inicializando repositório Git..."

git init
git branch -M main

echo "📁 Adicionando arquivos..."
git add .

echo "💾 Criando commit inicial..."
git commit -m "feat: projeto inicial EcoFleet UPX2

Sistema de gestão de frota com cálculo automático de emissões de CO₂.

- Spring Boot 3.2.5 + Java 21
- Spring Data JPA + SQLite
- Spring Security + Thymeleaf + Bootstrap 5.3
- Módulos: Veículos, Motoristas, Registros de Uso, Dashboard
- Cálculo automático: distância, consumo, custo (R\$6,19/L) e CO₂ (fatores IPCC 2023)"

echo "🔗 Conectando ao repositório remoto..."
git remote add origin https://github.com/pavanelloo/ecofleet-upx2.git

echo "🚀 Fazendo push..."
git push -u origin main

echo ""
echo "✅ Projeto publicado com sucesso!"
echo "👉 https://github.com/pavanelloo/ecofleet-upx2"
