import warnings

try:
    from pgmpy.models import BayesianNetwork

    alarm_model = BayesianNetwork([('Burglary', 'Alarm'),
                                   ('Earthquake', 'Alarm'),
                                   ('Alarm', 'JohnCalls'),
                                   ('Alarm', 'MaryCalls')])
except ImportError:
    from pgmpy.models import BayesianModel

    alarm_model = BayesianModel([('Burglary', 'Alarm'),
                                 ('Earthquake', 'Alarm'),
                                 ('Alarm', 'JohnCalls'),
                                 ('Alarm', 'MaryCalls')])

from pgmpy.inference import VariableElimination

if __name__ == '__main__':
    with warnings.catch_warnings():

        # ignore all caught warnings
        warnings.filterwarnings("ignore")

        # alarm_model atribuído

        from pgmpy.factors.discrete import TabularCPD

        cpd_burglary = TabularCPD(variable='Burglary', variable_card=2,
                                  values=[[.999], [0.001]])
        cpd_earthquake = TabularCPD(variable='Earthquake', variable_card=2,
                                    values=[[0.998], [0.002]])
        cpd_alarm = TabularCPD(variable='Alarm', variable_card=2,
                               values=[[0.999, 0.71, 0.06, 0.05],
                                       [0.001, 0.29, 0.94, 0.95]],
                               evidence=['Burglary', 'Earthquake'],
                               evidence_card=[2, 2])
        cpd_johncalls = TabularCPD(variable='JohnCalls', variable_card=2,
                                   values=[[0.95, 0.1], [0.05, 0.9]],
                                   evidence=['Alarm'], evidence_card=[2])
        cpd_marycalls = TabularCPD(variable='MaryCalls', variable_card=2,
                                   values=[[0.9, 0.7], [0.1, 0.3]],
                                   evidence=['Alarm'], evidence_card=[2])

        alarm_model.add_cpds(cpd_burglary, cpd_earthquake, cpd_alarm, cpd_johncalls, cpd_marycalls)

        # print(alarm_model.get_cpds())

        # print(alarm_model.check_model())
        #
        # print(alarm_model.nodes())
        #
        # print(alarm_model.edges())
        #
        # alarm_model.local_independencies('Burglary')
        #
        # print(alarm_model.get_independencies())

        # for dpc in alarm_model.get_cpds():
        #      print("DPC de {variable}:".format(variable=dpc.variable))
        #      print(dpc)

        # prob_alarm1 = infer.map_query(variables=['Alarm', 'JohnCalls', 'MaryCalls'], evidence={'Burglary':0, 'Earthquake':0})
        # # print(prob_alarm1)

        print("\n⇾ Problema do Alarme - Redes Bayesianas | Probabilidades Calculadas ⇽\n")

        infer = VariableElimination(alarm_model)

        prob_alarm0 = infer.query(variables=['Alarm', 'JohnCalls', 'MaryCalls'], evidence={'Burglary': 0, 'Earthquake': 0},
                                  show_progress=False)
        print("Exercício 1: Qual a probabilidade de não ocorrer um roubo,\nnem terremoto, mas tocar o alarme, e John e Mary liguarem?")
        print("Probabilidade de: " + "{:.3%}".format(prob_alarm0.get_value(Alarm=1, JohnCalls=1, MaryCalls=1)))

        print("\nExercício 2: Qual a probabilidade de não ocorrer um roubo,\nnem terremoto, mas tocar o alarme, e apenas John ligar?")
        print("")
        print("probabilidade de: " + "{:.3%}".format(prob_alarm0.get_value(Alarm=1, JohnCalls=1, MaryCalls=0)))

        print("\nExercício 3: Qual a probabilidade de não ocorrer um roubo,\nnem terremoto, mas tocar o alarme, e apenas Mary ligar?")
        print("Probabilidade de: " + "{:.3%}".format(prob_alarm0.get_value(Alarm=1, JohnCalls=0, MaryCalls=1)))

        prob_alarm2 = infer.query(variables=['JohnCalls', 'MaryCalls'], evidence={'Alarm': 0}, show_progress=False)

        print("\nExercício 4: Qual a probabilidade do alarme não tocar, e John e Mary ligarem?")
        print("Probabilidade de: " + "{:.1%}".format(prob_alarm2.get_value(JohnCalls=1, MaryCalls=1)))

        print("\nExercício 5: Qual a probabilidade do alarme não tocar, e apenas Mary ligar?")
        print("Probabilidade de: " + "{:.1%}".format(prob_alarm2.get_value(JohnCalls=0, MaryCalls=1)))

        print("\n⇾ INTELIGÊNCIA ARTIFICIAL - 2020.2 - FEITO POR: ARMSTRONG, DAVID E ISAAC ⇽\n")