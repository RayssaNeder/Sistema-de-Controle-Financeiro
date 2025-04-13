CREATE OR REPLACE PROCEDURE prc_integracao_dados IS
    v_id_cliente_pf cliente.id%TYPE;
    v_id_cliente_pj cliente.id%TYPE;
    v_id_conta_pf conta.id%TYPE;
    v_id_conta_pj conta.id%TYPE;
    v_id_endereco_pf endereco.id%TYPE;
    v_id_endereco_pj endereco.id%TYPE;
    v_id_conta_rayssa_1 conta.id%TYPE;
    v_id_conta_rayssa_2 conta.id%TYPE;
BEGIN
    -- Verifica se já existe cliente PF com CPF 12345678900
    BEGIN
        SELECT cpf.id
        INTO v_id_cliente_pf
        FROM cliente_pessoa_fisica cpf
        WHERE cpf.cpf = '12345678900'
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Inserção do cliente PF (Pessoa Física)
            INSERT INTO cliente (uuid, nome, email, telefone, data_cadastro, data_exclusao)
            VALUES (SYS_GUID(), 'João Silva', 'joao_silva@gmail.com', '83955847878', TRUNC(SYSDATE), NULL)
            RETURNING id INTO v_id_cliente_pf;

            INSERT INTO cliente_pessoa_fisica (id, cpf)
            VALUES (v_id_cliente_pf, '12345678900');

            -- Inserção do endereço para o cliente PF
            INSERT INTO endereco (uuid, rua, bairro, cep, numero, complemento, cidade, uf, cliente_id)
            VALUES (SYS_GUID(), 'Rua A', 'Bairro X', '12345678', '123', 'Complemento 1', 'Cidade Y', 'UF', v_id_cliente_pf)
            RETURNING id INTO v_id_endereco_pf;
    END;

    -- Criação da conta para o cliente PF (se ainda não existir)
    BEGIN
        SELECT id INTO v_id_conta_pf
        FROM conta
        WHERE cliente_id = v_id_cliente_pf
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            INSERT INTO conta (uuid, agencia, numero, ativa, cliente_id, data_exclusao)
            VALUES (SYS_GUID(), '1234', '56789', 1, v_id_cliente_pf, NULL)
            RETURNING id INTO v_id_conta_pf;

            -- Movimentação para conta PF
            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), TRUNC(SYSDATE), 0.00, 'Depósito inicial', 'CREDITO', v_id_conta_pf, NULL);
    END;

    -- Verifica se já existe cliente PJ com CNPJ 11222333000199
    BEGIN
        SELECT pj.id
        INTO v_id_cliente_pj
        FROM cliente_pessoa_juridica pj
        WHERE pj.cnpj = '11222333000199'
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Inserção do cliente PJ (Pessoa Jurídica)
            INSERT INTO cliente (uuid, nome, email, telefone, data_cadastro, data_exclusao)
            VALUES (SYS_GUID(), 'Empresa XYZ', 'xyz@gmail.com', '8395584784', TRUNC(SYSDATE), NULL)
            RETURNING id INTO v_id_cliente_pj;

            INSERT INTO cliente_pessoa_juridica (id, cnpj, razao_social)
            VALUES (v_id_cliente_pj, '11222333000199', 'Empresa XYZ Ltda');

            -- Inserção do endereço para o cliente PJ
            INSERT INTO endereco (uuid, rua, bairro, cep, numero, complemento, cidade, uf, cliente_id)
            VALUES (SYS_GUID(), 'Rua B', 'Bairro Z', '87654321', '321', 'Complemento 2', 'Cidade W', 'UF', v_id_cliente_pj)
            RETURNING id INTO v_id_endereco_pj;
    END;

    -- Criação da conta para o cliente PJ (se ainda não existir)
    BEGIN
        SELECT id INTO v_id_conta_pj
        FROM conta
        WHERE cliente_id = v_id_cliente_pj
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            INSERT INTO conta (uuid, agencia, numero, ativa, cliente_id, data_exclusao)
            VALUES (SYS_GUID(), '2345', '67890', 1, v_id_cliente_pj, NULL)
            RETURNING id INTO v_id_conta_pj;

            -- Movimentação para conta PJ
            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), TRUNC(SYSDATE), 0.00, 'Depósito inicial PJ', 'CREDITO', v_id_conta_pj, NULL);
    END;

    -- Verifica se já existe cliente PF com CPF 08055741484
    BEGIN
        SELECT pf.id
        INTO v_id_cliente_pf
        FROM cliente_pessoa_fisica pf
        WHERE pf.cpf = '08055741484'
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Inserção do cliente PF (Pessoa Física)
            INSERT INTO cliente (uuid, nome, email, telefone, data_cadastro, data_exclusao)
            VALUES (SYS_GUID(), 'Rayssa', 'rayssa@gmail.com', '08055741484', TRUNC(SYSDATE), NULL)
            RETURNING id INTO v_id_cliente_pf;

            INSERT INTO cliente_pessoa_fisica (id, cpf)
            VALUES (v_id_cliente_pf, '08055741484');

            -- Inserção do endereço para o cliente Rayssa
            INSERT INTO endereco (uuid, rua, bairro, cep, numero, complemento, cidade, uf, cliente_id)
            VALUES (SYS_GUID(), 'Rua das Margaridas', 'Bairro Novo', '87654321', '08', 'Bloco B', 'João Pessoa', 'Paraíba', v_id_cliente_pf)
            RETURNING id INTO v_id_endereco_pf;
    END;

    -- Criação das contas para o cliente Rayssa (se ainda não existir)
    BEGIN
        -- Conta 1
        SELECT id INTO v_id_conta_rayssa_1
        FROM conta
        WHERE cliente_id = v_id_cliente_pf
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada

        -- Conta 2
        SELECT id INTO v_id_conta_rayssa_2
        FROM conta
        WHERE cliente_id = v_id_cliente_pf
        AND ROWNUM = 1;  -- Garante que apenas uma linha será retornada
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Conta 1 do cliente Rayssa
            INSERT INTO conta (uuid, agencia, numero, ativa, cliente_id, data_exclusao)
            VALUES (SYS_GUID(), '0001', '3998-29', 1, v_id_cliente_pf, NULL)
            RETURNING id INTO v_id_conta_rayssa_1;

            -- Conta 2 do cliente Rayssa
            INSERT INTO conta (uuid, agencia, numero, ativa, cliente_id, data_exclusao)
            VALUES (SYS_GUID(), '0001', '7581-44', 1, v_id_cliente_pf, NULL)
            RETURNING id INTO v_id_conta_rayssa_2;

            -- Movimentações para contas de Rayssa
            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), TRUNC(SYSDATE), 0.00, 'Movimento inicial', 'CREDITO', v_id_conta_rayssa_1, NULL);

            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), TRUNC(SYSDATE), 1000.00, 'Recebimento de Salário', 'CREDITO', v_id_conta_rayssa_1, NULL);

            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), TRUNC(SYSDATE), 500.00, 'Pagamento de Aluguel', 'DEBITO', v_id_conta_rayssa_1, NULL);
    END;
   
     COMMIT;
END prc_integracao_dados;

