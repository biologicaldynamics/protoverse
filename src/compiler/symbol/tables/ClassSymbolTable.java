/*
 * Copyright (c) 2015 David Bruce Borenstein and the Trustees
 * of Princeton University. All rights reserved.
 */

package compiler.symbol.tables;

import com.google.common.reflect.TypeToken;
import compiler.symbol.symbols.ClassSymbol;
import compiler.util.UnrecognizedIdentifierError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
* Created by dbborens on 3/3/15.
*/
public abstract class ClassSymbolTable<T> implements ResolvingSymbolTable {

   private HashMap<String, ClassSymbol> members;
   private Logger logger;

   private final TypeToken<T> type = new TypeToken<T>(getClass()) {};

   public ClassSymbolTable() {
       members = resolveSubclasses();
       logger = LoggerFactory.getLogger(ClassSymbolTable.class);
   }

   protected abstract HashMap<String, ClassSymbol> resolveSubclasses();

   @Override
   public InstantiableSymbolTable getSymbolTable(String identifier) {
       logger.debug("Resolving \"{}\" against class {}", identifier,
               getBroadClass().getSimpleName());

       if (!members.containsKey(identifier)) {
           logger.error("Unable to resolve \"{}\" against class {}",
                   identifier, getBroadClass().getSimpleName());

           throw new UnrecognizedIdentifierError(identifier,
                   getBroadClass());
       }

       return members.get(identifier).getSymbolTable();
   }

   public Class getBroadClass() {
       return type.getRawType();
   }
}
