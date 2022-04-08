package com.practicalbusiness.study.jpa.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCA is a Querydsl query type for CA
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCA extends EntityPathBase<CA> {

    private static final long serialVersionUID = -1576907062L;

    public static final QCA cA = new QCA("cA");

    public final ListPath<Certificate, QCertificate> certificates = this.<Certificate, QCertificate>createList("certificates", Certificate.class, QCertificate.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QCA(String variable) {
        super(CA.class, forVariable(variable));
    }

    public QCA(Path<? extends CA> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCA(PathMetadata metadata) {
        super(CA.class, metadata);
    }

}

