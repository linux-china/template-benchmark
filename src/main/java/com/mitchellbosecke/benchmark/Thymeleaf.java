package com.mitchellbosecke.benchmark;

import freemarker.template.TemplateException;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

public class Thymeleaf extends BaseBenchmark {

    private TemplateEngine engine;

    private IContext context;

    TemplateSpec templateSpec;

    @Setup
    public void setup() throws IOException {
        engine = new TemplateEngine();
        engine.setTemplateResolver(new ClassLoaderTemplateResolver());
        context = new Context(Locale.getDefault(), getContext());
        templateSpec = new TemplateSpec("templates/stocks.thymeleaf.html", TemplateMode.HTML);
    }

    @Benchmark
    public String benchmark() throws TemplateException, IOException {
        Writer writer = new StringWriter();
        engine.process(templateSpec, context, writer);
        return writer.toString();
    }

}
