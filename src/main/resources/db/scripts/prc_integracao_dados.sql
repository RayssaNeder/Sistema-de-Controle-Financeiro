CREATE OR REPLACE PROCEDURE prc_integracao_dados IS
    v_id_cliente_pf cliente.id%TYPE;
    v_id_cliente_pj cliente.id%TYPE;
    v_id_conta_pf conta.id%TYPE;
    v_id_conta_pj conta.id%TYPE;
    v_id_endereco_pf endereco.id%TYPE;
    v_id_endereco_pj endereco.id%TYPE;
BEGIN
    -- Verifica se já existe cliente PF com CPF 12345678900
    BEGIN
        SELECT cpf.id
        INTO v_id_cliente_pf
        FROM cliente_pessoa_fisica cpf
        WHERE cpf.cpf = '12345678900';
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
        WHERE cliente_id = v_id_cliente_pf;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            INSERT INTO conta (uuid, agencia, numero, ativa, cliente_id, data_exclusao)
            VALUES (SYS_GUID(), '1234', '56789', 1, v_id_cliente_pf, NULL)
            RETURNING id INTO v_id_conta_pf;

            -- Movimentação para conta PF
            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), SYSDATE, 0.00, 'Depósito inicial', 'CREDITO', v_id_conta_pf, NULL);
    END;

    -- Verifica se já existe cliente PJ com CNPJ 11222333000199
    BEGIN
        SELECT pj.id
        INTO v_id_cliente_pj
        FROM cliente_pessoa_juridica pj
        WHERE pj.cnpj = '11222333000199';
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
        WHERE cliente_id = v_id_cliente_pj;

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            INSERT INTO conta (uuid, agencia, numero, ativa, cliente_id, data_exclusao)
            VALUES (SYS_GUID(), '2345', '67890', 1, v_id_cliente_pj, NULL)
            RETURNING id INTO v_id_conta_pj;

            -- Movimentação para conta PJ
            INSERT INTO movimentacao (uuid, data, valor, descricao, tipo, conta_id, data_exclusao)
            VALUES (SYS_GUID(), SYSDATE, 0.00, 'Depósito inicial PJ', 'CREDITO', v_id_conta_pj, NULL);
    END;

    COMMIT;
END;
