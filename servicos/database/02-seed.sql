\connect icomprasclientes

INSERT INTO clientes (nome, cpf, logradouro, numero, bairro, email, telefone)
VALUES
    ('Ana Beatriz Souza', '11122233344', 'Rua das Flores', '123', 'Centro', 'ana.souza@email.com', '11987654321'),
    ('Bruno Carvalho Lima', '22233344455', 'Avenida Paulista', '456', 'Bela Vista', 'bruno.lima@email.com', '11976543210'),
    ('Carla Mendes Rocha', '33344455566', 'Rua Sete de Setembro', '789', 'Savassi', 'carla.rocha@email.com', '31965432109'),
    ('Diego Ferreira Alves', '44455566677', 'Rua das Palmeiras', '321', 'Jardins', 'diego.alves@email.com', '11954321098'),
    ('Elisa Martins Pereira', '55566677788', 'Avenida Atlântica', '654', 'Copacabana', 'elisa.pereira@email.com', '21943210987');

\connect icomprasprodutos

INSERT INTO produtos (nome, descricao, valor_unitario)
VALUES
    ('Teclado Mecânico RGB', 'Teclado mecânico com switches azuis e iluminação RGB personalizável', 349.90),
    ('Mouse Gamer 16000 DPI', 'Mouse gamer com sensor óptico de alta precisão e 7 botões programáveis', 189.90),
    ('Monitor 27" 144Hz', 'Monitor Full HD IPS de 27 polegadas com taxa de atualização de 144Hz', 1299.00),
    ('Headset Sem Fio', 'Headset gamer sem fio com áudio surround 7.1 e microfone destacável', 459.90),
    ('Cadeira Ergonômica', 'Cadeira de escritório ergonômica com apoio lombar e ajuste de altura', 899.00);
