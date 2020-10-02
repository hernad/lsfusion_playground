package ba.out.lsfusion1.action;

import lsfusion.server.data.sql.exception.SQLHandledException;
import lsfusion.server.data.value.DataObject;
import lsfusion.server.language.ScriptingErrorLog;
import lsfusion.server.language.ScriptingLogicsModule;
import lsfusion.server.logics.action.controller.context.ExecutionContext;
import lsfusion.server.logics.action.session.DataSession;
import lsfusion.server.logics.classes.ValueClass;
import lsfusion.server.logics.property.classes.ClassPropertyInterface;
import lsfusion.server.physics.dev.integration.internal.to.InternalAction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

public class Action1 extends InternalAction {

    public final ClassPropertyInterface arg1Interface;
    public final ClassPropertyInterface arg2Interface;

    public Action1(ScriptingLogicsModule LM, ValueClass... classes) {
        super(LM, classes);

        Iterator<ClassPropertyInterface> i = interfaces.iterator();

        arg1Interface = i.next();
        arg2Interface = i.next();
    }

    @Override
    protected void executeInternal(ExecutionContext<ClassPropertyInterface> executionContext) throws SQLException, SQLHandledException {

        //try {
            DataSession session = executionContext.getSession();

            DataObject arg1 = executionContext.getDataKeyValue(arg1Interface);
            DataObject arg2 = executionContext.getDataKeyValue(arg2Interface);

            String sArg1 = (String) arg1.object;
            String sArg2 = (String) arg2.object;

            //String sArg1 = (String) findProperty("").read(session, arg1);
            //String sArg2 = (String) findProperty("").read(session, arg1);
            System.out.println("arg1: " + sArg1 + "; arg2:" + sArg2);

            try {
                findProperty("concatString[]").change( sArg1 + sArg2, session);
            } catch (ScriptingErrorLog.SemanticErrorException e) {
                e.printStackTrace();
            }

    }
}
