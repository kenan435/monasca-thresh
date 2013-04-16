package com.hpcloud.maas;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.hpcloud.maas.infrastructure.storm.amqp.AMQPSpoutConfiguration;
import com.hpcloud.messaging.rabbitmq.RabbitMQConfiguration;
import com.yammer.dropwizard.db.DatabaseConfiguration;

/**
 * Thresholding configuration.
 * 
 * @author Jonathan Halterman
 */
public class ThresholdingConfiguration {
  public static final String ALERTS_EXCHANGE = "thresh.external.alerts";
  public static final String ALERTS_ROUTING_KEY = "thresh.external.alert";

  /** Total number of workers processes across the cluster. */
  @NotNull public Integer numWorkerProcesses = 12;
  /** Total number of acker threads across the cluster. */
  @NotNull public Integer numAckerThreads = 12;

  @NotNull public Integer maasMetricSpoutThreads = 3;
  @NotNull public Integer maasMetricSpoutTasks = 3;

  @NotNull public Integer collectdMetricSpoutThreads = 3;
  @NotNull public Integer collectdMetricSpoutTasks = 3;

  @NotNull public Integer eventSpoutThreads = 1;
  @NotNull public Integer eventSpoutTasks = 1;

  @NotNull public Integer eventBoltThreads = 2;
  @NotNull public Integer eventBoltTasks = 2;

  @NotNull public Integer aggregationBoltThreads = 10;
  @NotNull public Integer aggregationBoltTasks = 10;

  @NotNull public Integer thresholdingBoltThreads = 3;
  @NotNull public Integer thresholdingBoltTasks = 3;

  /** Configuration for the spout that receives collectd metrics from the internal exchange. */
  @Valid @NotNull public AMQPSpoutConfiguration collectdMetricSpout;
  /** Configuration for the spout that receives MaaS metrics from the external exchange. */
  @Valid @NotNull public AMQPSpoutConfiguration maasMetricSpout;
  /** Configuration for the spout that receives MaaS events from the external exchange. */
  @Valid @NotNull public AMQPSpoutConfiguration eventSpout;

  /** Configuration for publishing to the alerts exchange on the external server. */
  @NotEmpty public String alertsExchange = "alerts";
  @NotEmpty public String alertsRoutingKey = "alert";
  @Valid @NotNull public RabbitMQConfiguration externalRabbit = new RabbitMQConfiguration();

  /** MaaS API database configuration. */
  @Valid @NotNull public DatabaseConfiguration database = new DatabaseConfiguration();
}
