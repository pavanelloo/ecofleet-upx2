# EcoFleet 🌿

**Sistema de Gestão de Frota com Inteligência Ambiental**

Projeto desenvolvido para a disciplina de Usina de Projetos Experimentais (UPX2) — FACENS, 2026.

---

## 📋 Sobre o Projeto

O EcoFleet é um sistema web para gestão de frotas corporativas que registra viagens e calcula automaticamente distância percorrida, consumo de combustível, custo financeiro e emissões de CO₂. Oferece um dashboard gerencial com indicadores consolidados e painel comparativo entre frotas a combustão e elétricas.

**Alinhado aos ODS 12 e 13 da Agenda 2030** — uso eficiente de recursos e redução de emissões no setor de transportes.

---

## 🛠️ Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.2.5 |
| Persistência | Spring Data JPA + SQLite |
| Segurança | Spring Security |
| Frontend | Thymeleaf + Bootstrap 5.3 |
| Build | Maven |

---

## 📦 Módulos

- **Veículos** — Cadastro e gerenciamento da frota (placa, modelo, tipo de combustível, consumo médio)
- **Motoristas** — Cadastro de motoristas habilitados
- **Registros de Uso** — Registro de saída/retorno com cálculo automático de distância, custo e CO₂
- **Dashboard** — Indicadores consolidados e comparativo elétrico vs. combustão

---

## ⚙️ Cálculos Automáticos

| Métrica | Fórmula |
|---------|---------|
| Distância | `km_retorno - km_saida` |
| Consumo (L) | `distancia / consumo_medio_veiculo` |
| Custo (R$) | `consumo × R$ 6,19/L` |
| CO₂ Gasolina | `consumo × 2,32 kg/L` (IPCC 2023) |
| CO₂ Etanol | `consumo × 1,55 kg/L` (IPCC 2023) |
| CO₂ Diesel | `consumo × 2,68 kg/L` (IPCC 2023) |
| Comparativo Elétrico | `6,5 km/kWh × R$ 0,80/kWh` |

---

## 🚀 Como Executar

### Pré-requisitos
- Java 21+
- Maven 3.8+

### Passos

```bash
# Clone o repositório
git clone https://github.com/pavanelloo/ecofleet-upx2.git
cd ecofleet-upx2

# Execute com Maven
./mvnw spring-boot:run
```

Acesse: **http://localhost:8080**

Credenciais padrão:
- Usuário: `admin`
- Senha: `admin123`

---

## 📁 Estrutura do Projeto

```
src/main/java/com/ecofleet/
├── EcoFleetApplication.java
├── controller/
│   ├── DashboardController.java
│   ├── MotoristaController.java
│   ├── RegistroUsoController.java
│   └── VeiculoController.java
├── model/
│   ├── Motorista.java
│   ├── RegistroUso.java
│   └── Veiculo.java
└── repository/
    ├── MotoristaRepository.java
    ├── RegistroUsoRepository.java
    └── VeiculoRepository.java

src/main/resources/
├── templates/
│   ├── dashboard.html
│   ├── layout.html
│   ├── motoristas.html
│   ├── registros.html
│   └── veiculos.html
└── application.properties
```

---

## 👥 Equipe

| Nome | RA |
|------|----|
| Gabriel Henrique Torres Pólvora | 264236 |
| Victor Luiz Brisolla | 264645 |
| Gustavo Gonçalves Miranda | 180366 |
| Victor Pavanello Amado | 263752 |

**Professora Orientadora:** Brenda Medina de Oliveira  
**Professor de Mentoria:** Daniel Ohata

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos — FACENS UPX2 2026.
