# Spring AI Project: Prompt Engineering for Text and Multimodal (Text + Image) Applications

---

## Overview
This project shows how to use **Spring AI** and **Spring Boot** to build REST endpoints for:

- Advanced prompt engineering for text-based LLMs (OpenAI, Ollama, etc.)
- Multimodal LLMs that accept both text **and** images as input
- Error-handling when working with file uploads / large payloads
- Interactive API testing with Swagger UI

---

## Features
- 🔡 **Text-based LLM inference** – ask questions and receive natural-language responses
- 🖼️ **Multimodal input** – send an image together with a prompt for richer context
- 📦 **Spring Boot REST API** with DI support for multiple AI clients (OpenAI, Ollama, …)
- 🧠 **Prompt Engineering** – system/user roles, examples, instructions,
- 🚦 **Swagger UI** – auto-generated OpenAPI docs for one-click testing

---

# clone & build
git clone https://github.com/your-org/your-repo.git
cd your-repo
mvn clean spring-boot:run