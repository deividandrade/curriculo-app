let formacaoIdx = 0;
let experienciaIdx = 0;
let currentSection = 0;

// ── Navegação entre seções ───────────────────────
function goToSection(index) {
    document.querySelectorAll('.form-section').forEach(s => s.classList.remove('active'));
    document.querySelectorAll('.step').forEach(s => {
        s.classList.remove('active');
        if (parseInt(s.dataset.section) < index) s.classList.add('done');
        else s.classList.remove('done');
    });

    document.getElementById('section-' + index).classList.add('active');
    document.querySelector('.step[data-section="' + index + '"]').classList.add('active');
    currentSection = index;
    window.scrollTo({ top: 0, behavior: 'smooth' });
}

// ── Formações ────────────────────────────────────
function adicionarFormacao() {
    const container = document.getElementById('formacoesContainer');
    const idx = formacaoIdx++;
    const div = document.createElement('div');
    div.className = 'entry-block';
    div.id = 'formacao-' + idx;
    div.innerHTML = `
        <div class="entry-block-header">
            <span class="entry-block-title">Formação ${idx + 1}</span>
            <button type="button" class="btn-remove" onclick="removerBloco('formacao-${idx}')">✕ Remover</button>
        </div>
        <div class="grid grid-2">
            <div class="field">
                <label>Instituição</label>
                <input type="text" name="instituicoes" placeholder="Ex: UFCG, UEPB..." />
            </div>
            <div class="field">
                <label>Curso</label>
                <input type="text" name="cursos" placeholder="Ex: Ciência da Computação" />
            </div>
        </div>
        <div class="grid grid-3">
            <div class="field">
                <label>Nível</label>
                <select name="niveis">
                    <option value="">Selecione...</option>
                    <option value="Técnico">Técnico</option>
                    <option value="Graduação">Graduação</option>
                    <option value="Pós-Graduação">Pós-Graduação</option>
                    <option value="MBA">MBA</option>
                    <option value="Mestrado">Mestrado</option>
                    <option value="Doutorado">Doutorado</option>
                </select>
            </div>
            <div class="field">
                <label>Ano de Início</label>
                <input type="text" name="anosInicio" placeholder="2020" maxlength="4" />
            </div>
            <div class="field">
                <label>Ano de Conclusão</label>
                <input type="text" name="anosConclusao" placeholder="2024 ou Em andamento" />
            </div>
        </div>
    `;
    container.appendChild(div);
    div.style.animation = 'fadeIn 0.3s ease both';
}

// ── Experiências ─────────────────────────────────
function adicionarExperiencia() {
    const container = document.getElementById('experienciasContainer');
    const idx = experienciaIdx++;
    const div = document.createElement('div');
    div.className = 'entry-block';
    div.id = 'experiencia-' + idx;
    div.innerHTML = `
        <div class="entry-block-header">
            <span class="entry-block-title">Experiência ${idx + 1}</span>
            <button type="button" class="btn-remove" onclick="removerBloco('experiencia-${idx}')">✕ Remover</button>
        </div>
        <div class="grid grid-2">
            <div class="field">
                <label>Empresa</label>
                <input type="text" name="empresas" placeholder="Nome da empresa" />
            </div>
            <div class="field">
                <label>Cargo</label>
                <input type="text" name="cargos" placeholder="Ex: Desenvolvedor Java Pleno" />
            </div>
        </div>
        <div class="grid grid-2">
            <div class="field">
                <label>Data de Início</label>
                <input type="text" name="datasInicio" placeholder="mm/aaaa" />
            </div>
            <div class="field">
                <label>Data de Saída</label>
                <input type="text" name="datasFim" placeholder="mm/aaaa ou Atual" />
            </div>
        </div>
        <div class="field">
            <label>Descrição das Atividades</label>
            <textarea name="descricoes" rows="3"
                placeholder="Descreva brevemente suas principais responsabilidades e conquistas..."></textarea>
        </div>
    `;
    container.appendChild(div);
    div.style.animation = 'fadeIn 0.3s ease both';
}

// ── Remover bloco ────────────────────────────────
function removerBloco(id) {
    const el = document.getElementById(id);
    if (el) {
        el.style.opacity = '0';
        el.style.transform = 'scale(0.97)';
        el.style.transition = 'all 0.2s ease';
        setTimeout(() => el.remove(), 200);
    }
}

// ── Contador de caracteres (objetivo) ────────────
const objetivo = document.getElementById('objetivo');
const counter = document.getElementById('objetivoCounter');
if (objetivo && counter) {
    objetivo.addEventListener('input', () => {
        counter.textContent = objetivo.value.length + ' / 1000';
        counter.style.color = objetivo.value.length > 900 ? '#c0392b' : '';
    });
}

// ── Preview de tags (habilidades / idiomas) ──────
function renderTags(inputId, previewId) {
    const input = document.getElementById(inputId);
    const preview = document.getElementById(previewId);
    if (!input || !preview) return;

    input.addEventListener('input', () => {
        const items = input.value.split(',').map(s => s.trim()).filter(Boolean);
        preview.innerHTML = items.map(item =>
            `<span class="tag">${item}</span>`
        ).join('');
    });
}

renderTags('habilidades', 'habilidadesPreview');
renderTags('idiomas', 'idiomasPreview');

// ── Máscara para telefone ─────────────────────────
const telefoneInput = document.getElementById('telefone');
if (telefoneInput) {
    telefoneInput.addEventListener('input', (e) => {
        let v = e.target.value.replace(/\D/g, '');
        if (v.length <= 10) {
            v = v.replace(/(\d{2})(\d{4})(\d{0,4})/, '($1) $2-$3');
        } else {
            v = v.replace(/(\d{2})(\d{5})(\d{0,4})/, '($1) $2-$3');
        }
        e.target.value = v;
    });
}

// ── Adicionar 1 formação e 1 experiência padrão ──
document.addEventListener('DOMContentLoaded', () => {
    adicionarFormacao();
    adicionarExperiencia();
});

// ── Navegação pelos steps na sidebar ────────────
document.querySelectorAll('.step').forEach(step => {
    step.addEventListener('click', () => {
        goToSection(parseInt(step.dataset.section));
    });
});

// ── Submit com loading ────────────────────────────
const form = document.getElementById('curriculoForm');
const submitBtn = document.getElementById('submitBtn');
if (form && submitBtn) {
    form.addEventListener('submit', () => {
        submitBtn.innerHTML = '<span>Salvando...</span>';
        submitBtn.disabled = true;
    });
}
