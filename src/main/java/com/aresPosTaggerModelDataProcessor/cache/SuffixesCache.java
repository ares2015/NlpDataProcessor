package com.aresPosTaggerModelDataProcessor.cache;

import com.aresPosTaggerModelDataProcessor.morphology.Suffixes;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Oliver on 3/20/2017.
 */
public final class SuffixesCache {

    public static Set<String> suffixesCache = new HashSet<String>();

    public static Set<Integer> suffixesLengthsModelMap = new HashSet<Integer>();

    static {
        initSuffixesCache();
        initSuffixesLengthCache();
    }

    private static void initSuffixesLengthCache() {
        /*
        As this application is used to read mainly Wikipedia articles, 's is used only to refer to possesion of something
		to some noun. It is not used as shortening for is/has.
		 */
        suffixesLengthsModelMap.add(Suffixes.ICS.length());

        suffixesLengthsModelMap.add(Suffixes.ACIES.length());

        suffixesLengthsModelMap.add(Suffixes.ASY.length());

        suffixesLengthsModelMap.add(Suffixes.ASIES.length());

        suffixesLengthsModelMap.add(Suffixes.FUL.length());

        suffixesLengthsModelMap.add(Suffixes.ITY.length());

        suffixesLengthsModelMap.add(Suffixes.ITIES.length());

        suffixesLengthsModelMap.add(Suffixes.ALLY.length());

        suffixesLengthsModelMap.add(Suffixes.TIONAL.length());

        suffixesLengthsModelMap.add(Suffixes.CIAL.length());

        suffixesLengthsModelMap.add(Suffixes.ANCE.length());

        suffixesLengthsModelMap.add(Suffixes.ANCES.length());

        suffixesLengthsModelMap.add(Suffixes.AL.length());

        suffixesLengthsModelMap.add(Suffixes.ENCE.length());

        suffixesLengthsModelMap.add(Suffixes.ENCES.length());

        suffixesLengthsModelMap.add(Suffixes.IC.length());

        suffixesLengthsModelMap.add(Suffixes.IER.length());

        suffixesLengthsModelMap.add(Suffixes.OR.length());

        suffixesLengthsModelMap.add(Suffixes.ORS.length());

        suffixesLengthsModelMap.add(Suffixes.EST.length());

        suffixesLengthsModelMap.add(Suffixes.IEST.length());

        suffixesLengthsModelMap.add(Suffixes.ED.length());

        suffixesLengthsModelMap.add(Suffixes.ING.length());

        suffixesLengthsModelMap.add(Suffixes.IEST.length());

    }

    private static void initSuffixesCache() {
        suffixesCache.add(Suffixes.ANCE);
        suffixesCache.add(Suffixes.ANCES);
        suffixesCache.add(Suffixes.ENCE);
        suffixesCache.add(Suffixes.ENCES);
        suffixesCache.add(Suffixes.S);
        suffixesCache.add(Suffixes.ES);
        suffixesCache.add(Suffixes.SES);
        suffixesCache.add(Suffixes.XES);
        suffixesCache.add(Suffixes.IES);
        suffixesCache.add(Suffixes.ING);
        suffixesCache.add(Suffixes.IC);
        suffixesCache.add(Suffixes.ER);
        suffixesCache.add(Suffixes.IER);
        suffixesCache.add(Suffixes.AL);
        suffixesCache.add(Suffixes.EST);
        suffixesCache.add(Suffixes.LY);
        suffixesCache.add(Suffixes.ED);
    }

}